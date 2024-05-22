package com.backend.apptive.exception;

public class BoardNotFoundException extends RuntimeException{
    public BoardNotFoundException(String message){
        super(message);
    }
}
