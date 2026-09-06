package com.aniket.ticketNest.repository;

import com.aniket.ticketNest.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, String> {
    List<Show> findByMovieId(String movieId);
}