package com.aniket.ticketNest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"show_id", "seat_number"}
        )
)
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    private BigDecimal price;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;
}
