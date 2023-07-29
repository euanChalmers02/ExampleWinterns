package com.example.mdbspringboot.controllers;

import com.example.mdbspringboot.model.ReceiptItem;
import com.example.mdbspringboot.model.ShoppingItem;
import com.example.mdbspringboot.services.TransacationService;
import com.example.mdbspringboot.services.UserInsightsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Insights")
@RestController
public class UserInsightsController {

    @Autowired
    UserInsightsService userInsightsService;

    @GetMapping("/userInsights/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Insights will be displayed in methods here";
    }

    @GetMapping("/userInsights/{userID}/mostPopularItems")
    public ResponseEntity<List<String>> mostPopularItems(@PathVariable("userID") String id){
        try {
            List<String> items = userInsightsService.showMostPopularItemCustomerBuys(id);
            return new ResponseEntity<>(items,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/userInsights/{userID}/getAllReceipts")
    public ResponseEntity<List<ReceiptItem>> getAllReceiptsForCard(@PathVariable("userID") String id){
        try {
            List<ReceiptItem> items = userInsightsService.showAllReceiptsByCardID(id);
            return new ResponseEntity<>(items,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
