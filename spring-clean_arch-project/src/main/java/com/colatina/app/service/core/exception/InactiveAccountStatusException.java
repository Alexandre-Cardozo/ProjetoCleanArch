package com.colatina.app.service.core.exception;

public class InactiveAccountStatusException extends RuntimeException{
    public InactiveAccountStatusException(String accountOperationType){
        super("The " + accountOperationType + " account isn't active");
    }
}
