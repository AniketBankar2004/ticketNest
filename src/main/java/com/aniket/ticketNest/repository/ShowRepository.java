package com.aniket.ticketNest.repository;

import com.aniket.ticketNest.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, String> {
}