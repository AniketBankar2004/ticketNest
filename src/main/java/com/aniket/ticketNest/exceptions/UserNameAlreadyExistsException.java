package com.aniket.ticketNest.exceptions;

public class UserNameAlreadyExistsException extends RuntimeException {

    public UserNameAlreadyExistsException(String message) {
        super(message);
    }

}
