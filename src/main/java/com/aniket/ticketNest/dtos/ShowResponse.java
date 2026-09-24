package com.aniket.ticketNest.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ShowResponse(
        String id,
        String movieId,
        String movieName,
        LocalDate showDate,
        LocalDateTime startTime

) {
}
