package com.aniket.shelfMate.repository;

import com.aniket.shelfMate.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, String> {
}