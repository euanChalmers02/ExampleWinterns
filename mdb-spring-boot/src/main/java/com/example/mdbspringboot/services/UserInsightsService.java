package com.example.mdbspringboot.services;

import com.example.mdbspringboot.model.ReceiptItem;
import com.example.mdbspringboot.model.ShoppingItem;
import com.example.mdbspringboot.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInsightsService {

    @Autowired
    ItemRepository receiptRepo;

//    change this to a repo where can write a query
    public List<ReceiptItem> showAllReceiptsByCardID(String cardID) {
        return receiptRepo.findAll().stream().filter(receipt -> receipt.getCardId().equals(cardID)).toList();
    }

    public List<ShoppingItem> showMostPopularItemCustomerBuys(String cardID) {
//        needs work to make fucntionq
        return receiptRepo.findAll().stream().filter(receipt -> receipt.getCardId().equals(cardID)).toList().get(0).getProducts();

    }


//    add as many as required here (contain all the models and functionality in methods here)





}
