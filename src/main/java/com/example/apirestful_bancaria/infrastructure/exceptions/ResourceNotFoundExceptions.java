package com.example.apirestful_bancaria.infrastructure.exceptions;

public class ResourceNotFoundExceptions extends  RuntimeException{
    public ResourceNotFoundExceptions(String mensagem){
        super(mensagem);
    }

    public ResourceNotFoundExceptions(String mensagem , Throwable throwable){
        super(mensagem , throwable);
    }
}
