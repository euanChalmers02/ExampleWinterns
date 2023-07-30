package com.example.mdbspringboot.controllers;

import com.example.mdbspringboot.model.ReceiptItem;
import com.example.mdbspringboot.model.ShoppingItem;
import com.example.mdbspringboot.services.TransactionService;
import com.example.mdbspringboot.services.UserInsightsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @GetMapping("/userInsights/{accountID}/mostPopularItems")
    @Operation(
            summary = "Provides most popular items for the account",
            description = "Enables insight of most popular item the customer buys")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Receipt successfully added"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<String>> mostPopularItems(
        @Parameter(
                name = "accountID",
                description = "The unique identifier of the account",
                required = true,
                example = "fb246b90-e549-44ef-831e-ea7fe8cf88c9")
        @PathVariable("accountID") String id){
        try {
            List<String> items = userInsightsService.showMostPopularItemCustomerBuys(id);
            return new ResponseEntity<>(items,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/userInsights/{accountID}/{transactionID}")
    @Operation(
            summary = "Get detailed Transaction data with itemised receipt",
            description = "Enables the combing of NatWest bank help transaction data and itemised receipt data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Data successfully combined and returned"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> combineData(
        @Parameter(
                name = "accountID",
                description = "The unique identifier of the account",
                required = true,
                example = "fb246b90-e549-44ef-831e-ea7fe8cf88c9")
        @PathVariable("accountID") String id,
        @Parameter(
                name = "transactionID",
                description = "The unique identifier of the transaction",
                required = true,
                example = "bd5e0498-21a3-400e-aa2d-1e747c16485f")
        @PathVariable("transactionID") String transactionID){
        try {
            var result = userInsightsService.combiningData(id,transactionID);
            return new ResponseEntity<>(result.toString(),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
