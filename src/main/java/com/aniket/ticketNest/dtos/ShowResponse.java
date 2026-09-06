package com.aniket.ticketNest.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public record ShowResponse(
        String id,
        String movieId,
        LocalDate showDate,
        LocalTime startTime,

) {
}
