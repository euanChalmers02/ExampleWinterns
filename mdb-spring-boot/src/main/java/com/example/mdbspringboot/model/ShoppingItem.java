package com.example.mdbspringboot.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Shopping Item")
public class ShoppingItem {

		@Schema(name="name")
		private String name;
		private int quantity;
		private String category;

		private float price;
		
		public ShoppingItem(String name, int quantity, String category, float price) {
			super();
			this.name = name;
			this.quantity = quantity;
			this.category = category;
			this.price = price;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getItemQuantity() {
			return quantity;
		}

		public void setItemQuantity(int quantity) {
			this.quantity = quantity;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

}
