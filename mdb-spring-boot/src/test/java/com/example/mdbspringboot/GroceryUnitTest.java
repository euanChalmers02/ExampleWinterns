package com.example.mdbspringboot;

import com.example.mdbspringboot.model.GroceryItem;
import com.example.mdbspringboot.repository.CustomItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GroceryUnitTest {

    @Autowired
    GroceryService groceryService;

    @BeforeEach
    public void setup(){

    }

    @Test
    public void getByName(){
        String expectedName = "Whole Wheat Biscuit";
        GroceryItem item = groceryService.getGroceryItemByName(expectedName);
        assertEquals(expectedName, item.getName());
    }

    @Test
    public void getByName_ReturnNull(){
        String expectedName = "Not an Item";
        GroceryItem item = groceryService.getGroceryItemByName(expectedName);
        assertNull(item);
    }

    @Test
    public void showAllGroceryItems(){
        int expectedSize = 5;
        int actualSize = groceryService.showAllGroceryItems().size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void getByCategory(){
        String expectedCategory = "munchies";
        int expectedSize = 2;
        int actualSize = groceryService.getItemsByCategory(expectedCategory).size();
        assertEquals(expectedSize, actualSize);
    }
}
