package com.aniket.shelfMate.repository;

import com.aniket.shelfMate.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie,String> {

}
