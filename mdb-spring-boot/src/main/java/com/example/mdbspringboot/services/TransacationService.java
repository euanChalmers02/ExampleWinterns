package com.example.mdbspringboot.services;

//import com.example.mdbspringboot.repository.CustomItemRepository;
import com.example.mdbspringboot.model.ReceiptItem;
import com.example.mdbspringboot.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacationService {

    @Autowired
    ItemRepository receiptRepo;


    //CREATE
    public void createItem(ReceiptItem item) {
        receiptRepo.save(item);
    }


    public List<ReceiptItem> showAllItems() {
        return receiptRepo.findAll();
    }


    // 3. Get name and items of a all items of a particular category
    public List<ReceiptItem> getItemsByCategory(String category) {
        return receiptRepo.findAll(category);
    }

    // 4. Get count of documents in the collection
    public long findCountOfItems() {
        long count = receiptRepo.count();
        return count;
    }

    // Print details in readable form
    public String getItemDetails(String id) {

        ReceiptItem item = receiptRepo.findById(id).get();

        System.out.println(
                "Item Name: " + item.getName() +
                        ", \nItem Category: " + item.getCategory()
        );

        return "";
    }

}
