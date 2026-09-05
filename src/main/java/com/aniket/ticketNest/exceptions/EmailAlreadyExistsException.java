package com.aniket.ticketNest.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String s) {
        super(s);
    }
}
