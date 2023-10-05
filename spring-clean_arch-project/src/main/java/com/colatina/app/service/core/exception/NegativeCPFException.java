package com.colatina.app.service.core.exception;

public class NegativeCPFException extends RuntimeException{
    public NegativeCPFException(){
        super("The user have the cpf negative");
    }
}
