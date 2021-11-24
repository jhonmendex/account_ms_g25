package com.misitiontic.account_ms.repositories;

import com.misitiontic.account_ms.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
   List<Transaction> findByusernameOrigin(String usernameOrigin);
    List<Transaction> findByusernameDestiny(String usernameDestiny);

}
