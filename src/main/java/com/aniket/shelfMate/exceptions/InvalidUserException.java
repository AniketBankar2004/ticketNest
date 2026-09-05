package com.aniket.shelfMate.exceptions;

public class InvalidUserException extends RuntimeException{
    public InvalidUserException(String invalidUsernameOrPassword) {
        super(invalidUsernameOrPassword);
    }
}
