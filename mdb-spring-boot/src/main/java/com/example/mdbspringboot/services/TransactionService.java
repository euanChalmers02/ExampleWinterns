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

}
