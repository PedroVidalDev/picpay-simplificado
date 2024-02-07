package com.pedro.picpay.infrastructure.exceptions;

public class TransactionValidation extends RuntimeException{
    public TransactionValidation(String s){
        super(s);
    }
}
