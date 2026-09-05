package com.aniket.shelfMate.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    private String seatNumber;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;
}
