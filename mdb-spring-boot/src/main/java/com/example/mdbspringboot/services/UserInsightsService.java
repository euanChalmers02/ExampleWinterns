package com.example.mdbspringboot.services;

import com.example.mdbspringboot.model.ReceiptItem;
import com.example.mdbspringboot.model.ShoppingItem;
import com.example.mdbspringboot.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        var res = getTopProducts(allProducts.toArray(ShoppingItem[]::new), 2);
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

}
