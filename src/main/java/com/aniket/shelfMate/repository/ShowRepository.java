package com.aniket.shelfMate.repository;

import com.aniket.shelfMate.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShowRepository extends JpaRepository<Show, String> {
}