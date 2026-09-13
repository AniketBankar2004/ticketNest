package com.aniket.ticketNest.repository;

import com.aniket.ticketNest.model.Ticket;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, String> {
    List<Ticket> findByBookingUserId(String userId);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        SELECT t
        FROM Ticket t
        WHERE t.show.id = :showId
        AND t.seatNumber = :seatNumber
    """)
    Optional<Ticket> findForUpdate(
            @Param("showId") String showId,
            @Param("seatNumber") String seatNumber
    );
}