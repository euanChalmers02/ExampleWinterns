package com.example.mdbspringboot.repository;

import java.util.List;

import com.example.mdbspringboot.model.ReceiptItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ItemRepository extends MongoRepository<ReceiptItem, String> {

	@Query(value="{accountId:'?0'}")
	List<ReceiptItem> findByAccountId(String accountId);

}
