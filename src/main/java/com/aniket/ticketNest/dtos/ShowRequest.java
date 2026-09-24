package com.aniket.ticketNest.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ShowRequest(
        String movieId,
        LocalDate showDate,
        LocalDateTime startTime) {
}