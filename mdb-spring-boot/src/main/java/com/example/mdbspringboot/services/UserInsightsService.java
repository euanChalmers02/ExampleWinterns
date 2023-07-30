package com.example.mdbspringboot.services;

import com.example.mdbspringboot.model.ReceiptItem;
import com.example.mdbspringboot.model.ShoppingItem;
import com.example.mdbspringboot.repository.ItemRepository;
import com.example.mdbspringboot.repository.NatwestApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import javax.json.*;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserInsightsService {

    @Autowired
    ItemRepository receiptRepo;

//  change this to a repo where can write a query
    public List<ReceiptItem> showAllReceiptsByCardID(String cardID) {
//      need to change to handle multiple users
        return receiptRepo.findAll().stream().toList();
    }

    public List<String> showMostPopularItemCustomerBuys(String cardID) {
        List<ShoppingItem> allProducts = new ArrayList<>();
        receiptRepo.findAll().forEach(receipt -> allProducts.addAll(receipt.getItems()));;
        var res = getTopProducts(allProducts.toArray(ShoppingItem[]::new), 3);
        System.out.println(res);
        return res;
    }

    public static List<String> getTopProducts(ShoppingItem[] items, int topCount) {
        Map<String, Long> nameCounts = Arrays.stream(items)
                .collect(Collectors.groupingBy(ShoppingItem::getName, Collectors.counting()));

        return nameCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(topCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public JsonObject combiningData(String accID, String transactionID) throws URISyntaxException, IOException, InterruptedException {

//        should not be hardcoded for prod
        var transaction = NatwestApiClient.getByAccountTransacationData("djefferson",accID);
        System.out.println(transaction);
        JsonReader jsonReader = Json.createReader(new StringReader(transaction));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        JsonArray transactions = jsonObject.getJsonObject("Data").getJsonArray("Transaction");
        JsonObject natwestTransaction = null;
        for (JsonObject transactionObject : transactions.getValuesAs(JsonObject.class)) {
            String accountId = transactionObject.getString("AccountId");
            String transactionId = transactionObject.getString("TransactionId");
            if (transactionId.equals(transactionID)) {
                natwestTransaction = transactionObject;
                break;
            }
        }

        var receipts = receiptRepo.findByAccountId(accID).stream().filter(receipt -> receipt.getTransactionId().equals(transactionID)).toList();
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder(natwestTransaction);


        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (ShoppingItem item : receipts.get(0).getItems()) {
            JsonObject jsonObjectItmes = Json.createObjectBuilder()
                    .add("name", item.getName())
                    .add("price", item.getPrice())
                    .add("quantity", item.getItemQuantity())
                    .build();
            jsonArrayBuilder.add(jsonObjectItmes);
        }
        jsonObjectBuilder.add("items",jsonArrayBuilder.build());
        JsonObject updatedNatwestTransaction = jsonObjectBuilder.build();

        return updatedNatwestTransaction;
    }

}
