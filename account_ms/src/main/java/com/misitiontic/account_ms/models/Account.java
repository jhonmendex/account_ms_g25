package com.misitiontic.account_ms.models;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Account {

   @Id
   private String username;
   private Integer balance;
   private Date date;

    public Account(String username, Integer balance, Date date){
        this.username = username;
        this.balance = balance;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
