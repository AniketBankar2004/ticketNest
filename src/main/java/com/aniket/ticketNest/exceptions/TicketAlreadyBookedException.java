package com.aniket.ticketNest.exceptions;

public class TicketAlreadyBookedException extends RuntimeException {
    public TicketAlreadyBookedException(String s) {
        super(s);
    }
}
