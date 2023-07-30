package com.example.mdbspringboot.controllers;

import com.example.mdbspringboot.model.ReceiptItem;
import com.example.mdbspringboot.model.ShoppingItem;
import com.example.mdbspringboot.services.TransactionService;
import com.example.mdbspringboot.services.UserInsightsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonObject;
import java.util.List;

@Tag(name = "User Insights")
@RestController
public class UserInsightsController {

    @Autowired
    UserInsightsService userInsightsService;

    @GetMapping("/userInsights/{userID}/mostPopularItems")
    @Operation(
            summary = "Provides most popular items for the account",
            description = "Enables insight of most popular item the customer buys")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Receipt successfully added"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<String>> mostPopularItems(@PathVariable("userID") String id){
        try {
            List<String> items = userInsightsService.showMostPopularItemCustomerBuys(id);
            return new ResponseEntity<>(items,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//Dynamic interaction of both //possibly remain  all to accountid
    @GetMapping("/userInsights/{accountID}/{transactionID}")
    @Operation(
            summary = "Get detailed Transaction data with itemised receipt",
            description = "Enables the combing of NatWest bank help transaction data and itemised receipt data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Data successfully combined and returned"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> combineData(@PathVariable("accountID") String id, @PathVariable("transactionID") String transactionID){
        try {
            var result = userInsightsService.combiningData(id,transactionID);
            return new ResponseEntity<>(result.toString(),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* ALREADY AVAILABLE IN ITEMISED RECEIPTS ENDPOINTS
    @GetMapping("/userInsights/{userID}/getAllReceipts")
    public ResponseEntity<List<ReceiptItem>> getAllReceiptsForCard(@PathVariable("userID") String id){
        try {
            List<ReceiptItem> items = userInsightsService.showAllReceiptsByCardID(id);
            return new ResponseEntity<>(items,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */

}
