package com.example.mdbspringboot;

import com.example.mdbspringboot.model.GroceryItem;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "GroceryOrderingAPP", description = "Euan Chalmers :)")
@RestController
public class GroceryController {

    @Autowired
    GroceryService groceryService;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello! %s", name);
    }

    @GetMapping("/items")
    public ResponseEntity<List<GroceryItem>> getAllItems(){
        try {
            List<GroceryItem> items = groceryService.showAllGroceryItems();
            return new ResponseEntity<>(items,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/items/{name}")
    public ResponseEntity<GroceryItem> getByName(@PathVariable("name") String name){
        try {
            GroceryItem item = groceryService.getGroceryItemByName(name);
            return new ResponseEntity<>(item,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/items/{category}")
    public ResponseEntity<List<GroceryItem>> getItemsByCategory(@PathVariable("category") String category){
        try {
            List<GroceryItem> items = groceryService.getItemsByCategory(category);
            return new ResponseEntity<>(items,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/items/{category}/{new}")
    public ResponseEntity<HttpStatus> updateCategory (@PathVariable("category") String category, @PathVariable("new") String newCategory){
        try {
            groceryService.updateCategoryName(category,newCategory);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/items")
    public ResponseEntity<HttpStatus> addItem(@RequestBody GroceryItem item){
        try {
            groceryService.createGroceryItem(item);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") String id){
        try {
            groceryService.deleteGroceryItem(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/items/count")
    public ResponseEntity<Long> getCount(){
        try {
            long count = groceryService.findCountOfGroceryItems();
            return new ResponseEntity<>(count,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("items/{id}")
    public  ResponseEntity<HttpStatus> updateQuantity(@PathVariable("id") String id, @RequestParam(value = "quantity")  int quantity){
        try {
            groceryService.updateItemQuantity(id,quantity);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("items/{id}/details")
    public ResponseEntity<String> getDetails(@PathVariable("id") String id){
        try {
            String details = groceryService.getItemDetails(id);
            return new ResponseEntity<>(details,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
