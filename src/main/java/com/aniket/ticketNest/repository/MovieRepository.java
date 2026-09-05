package com.aniket.ticketNest.repository;

import com.aniket.ticketNest.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,String> {

}
