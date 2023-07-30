package com.example.mdbspringboot.services;

//import com.example.mdbspringboot.repository.CustomItemRepository;
import com.example.mdbspringboot.model.ReceiptItem;
import com.example.mdbspringboot.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    ItemRepository receiptRepo;

    public void createItem(ReceiptItem item) {
        receiptRepo.save(item);
    }

    public List<ReceiptItem> showReceiptsForAccount(String accountId) {
        return receiptRepo.findByAccountId(accountId);
    }

    public List<ReceiptItem> showAllItems() {
        return receiptRepo.findAll();
    }

    // Get name and items of a all items of a particular category
    /*
    public List<ReceiptItem> getItemsByCategory(String category) {
        return receiptRepo.findAll(category);
    }
    */

    // Get count of documents in the collection
    /*
    public long findCountOfItems() {
        long count = receiptRepo.count();
        return count;
    }
    */

    // Print details in readable form
    /*
    public String getItemDetails(String id) {
        return null;
    }
    */

}
