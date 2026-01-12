package com.ipd.demo.exception;

public class AccessDeniedException extends RuntimeException{

    public AccessDeniedException(String message ){
        super(message);
    }
}
