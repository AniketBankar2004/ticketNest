package com.aniket.shelfMate.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String s) {
        super(s);
    }
}
