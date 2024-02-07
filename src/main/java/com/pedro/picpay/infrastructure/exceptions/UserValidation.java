package com.pedro.picpay.infrastructure.exceptions;


public class UserValidation extends RuntimeException{
    public UserValidation(String e){
        super(e);
    }
}
