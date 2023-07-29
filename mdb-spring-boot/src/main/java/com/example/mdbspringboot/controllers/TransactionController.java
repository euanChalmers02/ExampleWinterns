package com.example.mdbspringboot.controllers;

import com.example.mdbspringboot.services.TransacationService;
import com.example.mdbspringboot.model.ReceiptItem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Detailed Transaction System")
@RestController
public class TransactionController {

    @Autowired
    TransacationService transacationService;

    @GetMapping("/hello")
    @Operation(
            summary = "This is a simple hello world that gets the status of the server",
            description = "Will return hello world or hello <your name> if you pass query param of your name")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello! %s", name);
    }

    @GetMapping("/receipts")
    public ResponseEntity<List<ReceiptItem>> getAllItems(){
        try {
            List<ReceiptItem> items = transacationService.showAllItems();
            return new ResponseEntity<>(items,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/receipts/{category}")
    public ResponseEntity<List<ReceiptItem>> getItemsByCategory(@PathVariable("category") String category){
        try {
            List<ReceiptItem> items = transacationService.getItemsByCategory(category);
            return new ResponseEntity<>(items,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/receipts")
    public ResponseEntity<HttpStatus> addItem(@RequestBody ReceiptItem item){
        try {
            transacationService.createItem(item);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/receipts/count")
    public ResponseEntity<Long> getCount(){
        try {
            long count = transacationService.findCountOfItems();
            return new ResponseEntity<>(count,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("receipts/{id}/details")
    public ResponseEntity<String> getDetails(@PathVariable("id") String id){
        try {
            String details = transacationService.getItemDetails(id);
            return new ResponseEntity<>(details,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

////    move to customer controller???
//    @GetMapping("/customer/{id}/CustomerInsightsALL")
//    public ResponseEntity<List<GroceryItem>> getCustomerInsights(@PathVariable("id") String id){
//        try {
////            fucntionalitly call here
//            return new ResponseEntity<>(items,HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
