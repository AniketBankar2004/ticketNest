package com.aniket.ticketNest.dtos;

import java.util.List;

public record BookingRequest(
        List<String> seatNumbers
) {
}
