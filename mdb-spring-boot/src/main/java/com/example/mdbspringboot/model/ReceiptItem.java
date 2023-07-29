package com.example.mdbspringboot.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Document("ReceiptItem")
public class ReceiptItem {

		@Id
		@Schema(name="card number (transaction id)")
		private String id;

		@Schema(name="name merchant")
		private String merchantName;

		@Schema(name="merchant category")
		private String category;

		@Schema(name = "date")
		private LocalDate date;

		@Schema(name = "Array of items bought")
		private ArrayList<ShoppingItem> items;

		@Schema(name = "time")
		private LocalTime time;

		@Schema(name = "Card number")
		private String cardID;



	public ReceiptItem(String id, String name, String category, ArrayList<ShoppingItem> items, LocalDate date, LocalTime time, String cardID){
			super();
			this.id = id;
			this.merchantName = name;
			this.category = category;
			this.date = date;
			this.time = time;
			this.items = items;
			this.cardID = cardID;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return merchantName;
		}

		public void setName(String name) {
			this.merchantName = name;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getCardId(){
			return cardID;
		}

		public List<ShoppingItem> getProducts(){
			return items;
		}

}
