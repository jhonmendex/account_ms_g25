package com.misitiontic.account_ms.controllers;


import com.misitiontic.account_ms.exceptions.AccountNotFoundException;
import com.misitiontic.account_ms.repositories.AccountRepository;
import org.springframework.web.bind.annotation.*;
import com.misitiontic.account_ms.models.Account;

@RestController
public class AccountController {

    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    ///account/jmendez
    @GetMapping("/account/{username}")
    public Account getAccount(@PathVariable String username) {
        return accountRepository.findById(username).orElseThrow(() -> new AccountNotFoundException("No se encontró un un dato con ese nombre de usuario " + username));
        //select * from account where username = "jmendez"
        // db.account.find({"username":"jmendez"})
    }

    /*
     {
      "username":"jmendez",
      "balance": 500000,
      "date": "2012-01-01"
     }
    * */
    @PostMapping("/account")
    public Account newAccount(@RequestBody Account account) {
        Account verifyAccount = accountRepository.findById(account.getUsername()).orElse(null);
        if (verifyAccount != null)
            throw new AccountNotFoundException("Error! Ya existe una cuenta con el nombre de " + account.getUsername());
        return accountRepository.save(account);
    }

    @DeleteMapping("/account/{username}")
    public String deleteAccount(@PathVariable String username) {
        Account verifyAccount = accountRepository.findById(username).orElse(null);
        if (verifyAccount == null)
            throw new AccountNotFoundException("Error! No existe una cuenta con el nombre de " + username);
        accountRepository.deleteById(username);
        return ("Usuario"+username+" eliminado");
    }



}
