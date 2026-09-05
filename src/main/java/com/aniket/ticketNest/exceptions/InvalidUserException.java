package com.aniket.ticketNest.exceptions;

public class InvalidUserException extends RuntimeException{
    public InvalidUserException(String invalidUsernameOrPassword) {
        super(invalidUsernameOrPassword);
    }
}
