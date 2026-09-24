package com.aniket.ticketNest.model;

import com.aniket.ticketNest.dtos.ShowResponse;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Table(name = "shows")
@Data
@Entity
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    private LocalDate showDate;

    private LocalDateTime startTime;

    @OneToMany(mappedBy = "show")
    private List<Ticket> tickets = new ArrayList<>();

    public ShowResponse toShowResponse() {
        return new ShowResponse(
                this.id,
                this.movie.getId(),
                this.movie.getTitle(),
                this.showDate,
                this.startTime
        );
    }
}
