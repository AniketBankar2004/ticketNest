package com.aniket.ticketNest.dtos;

import com.aniket.ticketNest.model.Show;
import com.aniket.ticketNest.model.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {

    private String id;

    private String showId;

    private String seatNumber;

    private BigDecimal price;

    private TicketStatus status;
}
