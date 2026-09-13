package com.aniket.ticketNest.dtos;

import java.util.List;

public record TicketBookingRequest(
        List<String> seatNumbers
) {
}
