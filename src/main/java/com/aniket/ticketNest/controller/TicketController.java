package com.aniket.ticketNest.controller;

import com.aniket.ticketNest.dtos.TicketBookingRequest;
import com.aniket.ticketNest.dtos.TicketResponse;
import com.aniket.ticketNest.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/shows/{showId}/tickets")
    public ResponseEntity<List<TicketResponse>> getTicketsByShow(
            @PathVariable String showId
    ){
        return ResponseEntity.ok(ticketService.getTicketByShow(showId));
    }

    @GetMapping("/tickets/{ticketId}")
    public ResponseEntity<TicketResponse> getTicketById(
            @PathVariable String ticketId
    ) {
        return ResponseEntity.ok(ticketService.getTicketById(ticketId));
    }

    @GetMapping("/tickets/my")
    public ResponseEntity<List<TicketResponse>> getMyTickets(
            Authentication authentication) {

        String userId = authentication.getName();

        return ResponseEntity.ok(
                ticketService.getMyTickets(userId)
        );
    }


}
