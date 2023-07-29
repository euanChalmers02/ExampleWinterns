package com.example.mdbspringboot.controllers;

import com.example.mdbspringboot.model.ReceiptItem;
import com.example.mdbspringboot.services.TransacationService;
import com.example.mdbspringboot.services.UserInsightsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Insights", description = "(add info here)")
@RestController
public class UserInsightsController {

    @Autowired
    UserInsightsService userInsightsService;

    @GetMapping("/userInsights/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Insights will be displayed in methods here";
    }


}
