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

		@Schema(name = "Array of items bought")
		private ArrayList<ShoppingItem> items;

		@Schema(name = "Card number")
		private String cardID;



	public ReceiptItem(String id, ArrayList<ShoppingItem> items, String cardID){
			super();
			this.id = id;
			this.items = items;
			this.cardID = cardID;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getCardId(){
			return cardID;
		}

		public List<ShoppingItem> getProducts(){
			return items;
		}

}
