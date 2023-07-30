package com.example.mdbspringboot.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("Receipt Item")
public class ReceiptItem {

	@Id
//	@Schema(name = "unique Identifier", description = "Autogenerated MongoDB objectID, prevents intentional overwrites" )
	private String id;

	@Schema(name = "items", description = "List of items bought in the transaction" ,required = true)
	private ArrayList<ShoppingItem> items;

	@Schema(name="account id", description = "Unique identifier of the account", example = "fb246b90-e549-44ef-831e-ea7fe8cf88c9", required = true)
	private String accountId;

	@Schema(name="transactionId", description = "Unique identifier of the transaction", example = "bd5e0498-21a3-400e-aa2d-1e747c16485f", required = true)
	private String transactionId;

	public ReceiptItem(String transactionId, ArrayList<ShoppingItem> items, String accountId){
			super();
			this.items = items;
			this.accountId = accountId;
			this.transactionId = transactionId;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTransactionId(){
			return transactionId;
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
