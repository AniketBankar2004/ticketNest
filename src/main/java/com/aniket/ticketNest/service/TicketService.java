package com.aniket.ticketNest.service;

import com.aniket.ticketNest.dtos.TicketResponse;
import com.aniket.ticketNest.exceptions.ShowNotFoundException;
import com.aniket.ticketNest.exceptions.TicketAlreadyBookedException;
import com.aniket.ticketNest.model.Show;
import com.aniket.ticketNest.model.Ticket;
import com.aniket.ticketNest.model.TicketStatus;
import com.aniket.ticketNest.repository.ShowRepository;
import com.aniket.ticketNest.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final ShowRepository showRepository;
    private final TicketRepository ticketRepository;

    public List<TicketResponse> getTicketByShow(String showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(()->new ShowNotFoundException("Show not found: "+showId));

        return show.getTickets()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TicketResponse getTicketById(String ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(()->new ShowNotFoundException("Ticket not found: "+ticketId));

        return toResponse(ticket);
    }

    private TicketResponse toResponse(Ticket ticket){
        return TicketResponse.builder()
                .id(ticket.getId())
                .showId(ticket.getShow().getId())
                .seatNumber(ticket.getSeatNumber())
                .status(ticket.getStatus())
                .price(ticket.getPrice())
                .build();
    }

    public List<TicketResponse> getMyTickets(String userId) {

        return ticketRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }


}
