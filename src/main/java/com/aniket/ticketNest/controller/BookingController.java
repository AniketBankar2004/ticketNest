package com.aniket.ticketNest.controller;

import com.aniket.ticketNest.dtos.BookingRequest;
import com.aniket.ticketNest.dtos.BookingResponse;
import com.aniket.ticketNest.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/shows/{showId}/book")
    public ResponseEntity<BookingResponse> bookTickets(
            @PathVariable String showId,
            @RequestBody BookingRequest request,
            Authentication authentication
    ){
        String userId = authentication.getName();

        return ResponseEntity.ok(bookingService.bookTickets(
                showId,
                request.seatNumbers(),
                userId
        ));
    }

    @DeleteMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingResponse> cancelBooking(
            @PathVariable String bookingId,
            Authentication authentication
    ) {
        String userId = authentication.getName();

        BookingResponse response = bookingService.cancelBooking(
                bookingId,
                userId
        );

        return ResponseEntity.ok(response);
    }
}
