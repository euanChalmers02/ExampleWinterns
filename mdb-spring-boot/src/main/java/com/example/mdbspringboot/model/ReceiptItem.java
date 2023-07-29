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
		@Schema(name="transaction id")
		private String id;

		@Schema(name = "Array of items bought")
		private ArrayList<ShoppingItem> items;

		@Schema(name = "account id")
		private String accountID;


	public ReceiptItem(String id, ArrayList<ShoppingItem> items, String accountID){
			super();
			this.id = id;
			this.items = items;
			this.accountID = accountID;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getAccountId(){
			return accountID;
		}

		public List<ShoppingItem> getProducts(){
			return items;
		}

}
