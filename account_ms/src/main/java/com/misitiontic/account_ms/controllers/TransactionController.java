package com.misitiontic.account_ms.controllers;

import com.misitiontic.account_ms.exceptions.AccountNotFoundException;
import com.misitiontic.account_ms.exceptions.InsufficientBalanceException;
import com.misitiontic.account_ms.models.Account;
import com.misitiontic.account_ms.models.Transaction;
import com.misitiontic.account_ms.repositories.AccountRepository;
import com.misitiontic.account_ms.repositories.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class TransactionController {

    TransactionRepository transactionRepository;
    AccountRepository accountRepository;


    public TransactionController(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

 ///db.transaction.find({"usernameOrigin":"jmendez","usernameDestiny":"jmendez"})
    @GetMapping("/transaction/{username}")
    List<Transaction> queryTransaction(@PathVariable String username) {
        List<Transaction> transactionsOrigin = transactionRepository.findByusernameOrigin(username);
        List<Transaction> transactionsDestiny = transactionRepository.findByusernameDestiny(username);
        List<Transaction> transaction = Stream.concat(transactionsOrigin.stream(),transactionsDestiny.stream()).collect(Collectors.toList());
        System.out.println(transaction);
        return transaction;
    }
/*
    {
            "usernameOrigin": "jmendez",
            "usernameDestiny": "wiliana",
            "value": 5000000,
            "date": "2021-20-11"
    }*/
    @PostMapping("/transactions")
    Transaction newTransaction(@RequestBody Transaction transaction){
        Account accoutOrigin = accountRepository.findById(transaction.getUsernameOrigin()).orElse(null);
        Account accoutDestiny = accountRepository.findById(transaction.getUsernameDestiny()).orElse(null);
        //cuenta origen que no existe
        if (accoutOrigin == null)
            throw new AccountNotFoundException("La cuenta " + transaction.getUsernameOrigin()+" no existe");
        //cuenta destino que no existe
        if (accoutDestiny == null)
            throw new AccountNotFoundException("La cuenta " + transaction.getUsernameDestiny()+" no existe");
        // actualiza el saldo de la cuenta origen ej. saldo 100     -   50 (giro) = 50 (nuevo saldo)

        //saldo insuficiente 100 - 200 = error
        if(accoutOrigin.getBalance() < transaction.getValue()){
           throw  new InsufficientBalanceException("saldo insuficiente");
        }

        accoutOrigin.setBalance(accoutOrigin.getBalance()-transaction.getValue());
        accoutOrigin.setDate(new Date());
        accountRepository.save(accoutOrigin);

        // actualiza el saldo de la cuenta destino ej. saldo 100    +   50 (giro) = 150 (nuevo saldo)
        accoutDestiny.setBalance(accoutDestiny.getBalance()+transaction.getValue());
        accoutDestiny.setDate(new Date());
        accountRepository.save(accoutDestiny);

        transaction.setDate(new Date());

        return transactionRepository.save(transaction);
    }

}
