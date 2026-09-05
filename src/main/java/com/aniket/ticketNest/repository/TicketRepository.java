package com.aniket.ticketNest.repository;

import com.aniket.ticketNest.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, String> {
}