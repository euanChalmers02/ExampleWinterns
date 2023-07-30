package com.example.mdbspringboot.controllers;

import com.example.mdbspringboot.services.TransactionService;
import com.example.mdbspringboot.model.ReceiptItem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Itemised receipts")
@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/receipt")
    @Operation(
        summary = "Send a receipt for a transaction",
        description = "Enables sending of itemised receipts which correspond to their related transactions")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "202", description = "Receipt successfully added"), 
        @ApiResponse(responseCode = "500", description = "Internal server error") 
    })
    public ResponseEntity<HttpStatus> addItem(@RequestBody ReceiptItem item){
        try {
            transactionService.createItem(item);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("receipts/{account_id}")
    @Operation(
        summary = "Get all receipts for an account",
        description = "Enables retrieval of all receipts associated with an account id")
    @ApiResponses(value = { 
        @ApiResponse(
            responseCode = "200", 
            description = "Receipts successfully retrieved", 
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ReceiptItem.class)
        )), 
        @ApiResponse(
            responseCode = "500", 
            description = "Internal server error"
        ) 
    })
    public ResponseEntity<List<ReceiptItem>> getReceiptsForAccount(
        @Parameter(
        description = "The unique identifier of the account",
        required = true,
        example = "fb246b90-e549-44ef-831e-ea7fe8cf88c9")
        @PathVariable("account_id") String id){
        try {
            List<ReceiptItem> receipts = transactionService.showReceiptsForAccount(id);
            return new ResponseEntity<>(receipts,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/receipts")
    @Operation(
        summary = "Get receipts for all transactions",
        description = "Enables retrieval of itemised receipts for all transactions")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Receipts successfully retrieved"), 
        @ApiResponse(responseCode = "500", description = "Internal server error") 
    })
    public ResponseEntity<List<ReceiptItem>> getAllItems(){
        try {
            List<ReceiptItem> items = transactionService.showAllItems();
            return new ResponseEntity<>(items,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* DETAILS SERVICE NOT DEVELOPED
    @GetMapping("receipts/{id}/details")
    public ResponseEntity<String> getDetails(@PathVariable("id") String id){
        try {
            String details = transactionService.getItemDetails(id);
            return new ResponseEntity<>(details,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */

    /* CATEGORY FOR RECEIPTS NOT DEVELOPED
    @GetMapping("/receipts/{category}")
    public ResponseEntity<List<ReceiptItem>> getItemsByCategory(@PathVariable("category") String category){
        try {
            List<ReceiptItem> items = transactionService.getItemsByCategory(category);
            return new ResponseEntity<>(items,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */

    /* COUNT NOT NEEDED FOR NOW
    @GetMapping("/receipts/count")
    public ResponseEntity<Long> getCount(){
        try {
            long count = transactionService.findCountOfItems();
            return new ResponseEntity<>(count,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */

}
