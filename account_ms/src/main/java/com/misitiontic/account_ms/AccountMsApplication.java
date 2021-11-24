package com.misitiontic.account_ms;

import com.misitiontic.account_ms.models.Account;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class AccountMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountMsApplication.class, args);
        //Account micuenta = new Account("jhonm",20000, new Date("2021-02-02"));
    }


}
