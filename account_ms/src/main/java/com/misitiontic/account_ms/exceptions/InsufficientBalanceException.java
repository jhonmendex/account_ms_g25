package com.misitiontic.account_ms.exceptions;

public class InsufficientBalanceException extends  RuntimeException {
    public InsufficientBalanceException(String message){
        super(message);
    }
}
