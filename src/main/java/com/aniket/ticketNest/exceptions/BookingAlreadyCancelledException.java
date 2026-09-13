package com.aniket.ticketNest.exceptions;

public class BookingAlreadyCancelledException extends RuntimeException {
    public BookingAlreadyCancelledException(String s) {
        super(s);
    }
}
