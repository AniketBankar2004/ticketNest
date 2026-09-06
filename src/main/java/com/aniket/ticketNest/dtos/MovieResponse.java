package com.aniket.ticketNest.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieResponse {
    private String id;

    private String title;

    private String description;

    private Integer duration;

    private String genre;

    private String language;

    private LocalDate releaseDate;

}
