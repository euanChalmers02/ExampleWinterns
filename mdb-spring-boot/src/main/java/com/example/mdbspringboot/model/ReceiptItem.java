package com.example.mdbspringboot.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Document("Receipt Item")
public class ReceiptItem {

	@Id
	@Schema(name="transaction id", description = "Unique identifier of the transaction", example = "2ee87f19-75e8-48c8-8bf6-b252f391d3ae", required = true)
	private String id;

	@Schema(name = "items", description = "List of items bought in the transaction" ,required = true)
	private ArrayList<ShoppingItem> items;

	@Schema(name="account id", description = "Unique identifier of the account", example = "fb246b90-e549-44ef-831e-ea7fe8cf88c9", required = true)
	private String accountId;


	public ReceiptItem(String id, ArrayList<ShoppingItem> items, String accountId){
			super();
			this.id = id;
			this.items = items;
			this.accountId = accountId;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getAccountId(){
			return accountId;
		}

		public void setAccountId(String accountId){
			this.accountId = accountId;
		}

		public List<ShoppingItem> getItems(){
			return items;
		}

}
