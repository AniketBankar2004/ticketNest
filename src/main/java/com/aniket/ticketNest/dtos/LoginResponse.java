package com.aniket.ticketNest.dtos;


public record LoginResponse(
        String token,
        String username
) {
}
