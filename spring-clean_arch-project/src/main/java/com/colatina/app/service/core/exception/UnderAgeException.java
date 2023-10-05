package com.colatina.app.service.core.exception;

public class UnderAgeException extends RuntimeException{
    public UnderAgeException(){
        super("The user is under age, cannot create an account");
    }
}
