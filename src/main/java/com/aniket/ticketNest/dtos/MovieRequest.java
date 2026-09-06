package com.aniket.ticketNest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MovieRequest(
         @NotBlank(message = "Please provide a title")
         String title,

         String description,

         Integer duration,

         String genre,

         String language,

         LocalDate releaseDate
) {
}
