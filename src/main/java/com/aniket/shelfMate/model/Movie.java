package com.aniket.shelfMate.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    private String description;

    private Integer duration;

    private String genre;

    private String language;

    private LocalDate releaseDate;

    @OneToMany(mappedBy = "movie")
    private List<Show> shows = new ArrayList<>();
}
