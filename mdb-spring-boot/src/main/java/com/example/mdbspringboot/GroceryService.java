package com.example.mdbspringboot;

import com.example.mdbspringboot.model.GroceryItem;
import com.example.mdbspringboot.repository.CustomItemRepository;
import com.example.mdbspringboot.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GroceryService {

    @Autowired
    ItemRepository groceryItemRepo;

    @Autowired
    CustomItemRepository customRepo;

    List<GroceryItem> itemList = new ArrayList<GroceryItem>();

    //CREATE
    public void createGroceryItem(GroceryItem item) {
        groceryItemRepo.save(item);
    }

    // READ
    // 1. Show all the data
    public List<GroceryItem> showAllGroceryItems() {
        return groceryItemRepo.findAll();
    }

    // 2. Get item by name
    public GroceryItem getGroceryItemByName(String name) {
        return groceryItemRepo.findItemByName(name);
    }

    // 3. Get name and items of a all items of a particular category
    public List<GroceryItem> getItemsByCategory(String category) {
        return groceryItemRepo.findAll(category);
    }

    // 4. Get count of documents in the collection
    public long findCountOfGroceryItems() {
        long count = groceryItemRepo.count();
        return count;
    }

    // UPDATE APPROACH 1: Using MongoRepository
    public void updateCategoryName(String category, String newCategory){

        // Find all the items with the category
        List<GroceryItem> list = groceryItemRepo.findAll(category);

        list.forEach(item -> {
            // Update the category in each document
            item.setCategory(newCategory);
        });

        // Save all the items in database
        List<GroceryItem> itemsUpdated = groceryItemRepo.saveAll(list);

        if(itemsUpdated != null)
            System.out.println("Successfully updated " + itemsUpdated.size() + " items.");
    }


    // UPDATE APPROACH 2: Using MongoTemplate
    public void updateItemQuantity(String name, float newQuantity) {
        System.out.println("Updating quantity for " + name);
        customRepo.updateItemQuantity(name, newQuantity);
    }

    // DELETE
    public void deleteGroceryItem(String id) {
        groceryItemRepo.deleteById(id);
        System.out.println("Item with id " + id + " deleted...");
    }
    // Print details in readable form

    public String getItemDetails(String id) {

        GroceryItem item = groceryItemRepo.findById(id).get();

        System.out.println(
                "Item Name: " + item.getName() +
                        ", \nItem Quantity: " + item.getItemQuantity() +
                        ", \nItem Category: " + item.getCategory()
        );

        return "";
    }

}
