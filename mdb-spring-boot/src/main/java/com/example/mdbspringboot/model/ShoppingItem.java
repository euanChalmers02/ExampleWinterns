package com.example.mdbspringboot.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Shopping Item")
public class ShoppingItem {

	@Schema(name="name", description = "Identifying name for the item", example = "Kellogg's Corn Flakes 750g", required = true)
	private String name;
	@Schema(name="price", description = "Price of the item (currency is fetched from the transaction)", example = "2.3", required = true)
	private Float price;
	@Schema(name="quantity", description = "Quantity of item bought", defaultValue = "1", example = "2", required = false)
	private Integer quantity;
	
	public ShoppingItem(String name, Float price, Integer quantity) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Integer getItemQuantity() {
		return quantity;
	}

	public void setItemQuantity(int quantity) {
		this.quantity = quantity;
	}

}
