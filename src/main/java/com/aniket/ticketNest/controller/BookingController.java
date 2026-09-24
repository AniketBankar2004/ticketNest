package com.aniket.ticketNest.controller;

import com.aniket.ticketNest.dtos.BookingRequest;
import com.aniket.ticketNest.dtos.BookingResponse;
import com.aniket.ticketNest.model.User;
import com.aniket.ticketNest.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
        User user = (User) authentication.getPrincipal();

        String userId = user.getId().toString();

        return ResponseEntity.ok(bookingService.bookTickets(
                showId,
                request.seatNumbers(),
                userId
        ));
    }

    @GetMapping("/bookings/my")
    public ResponseEntity<List<BookingResponse>> getMyBookings(
            Authentication authentication
    ){
        User user = (User) authentication.getPrincipal();

        String userId = user.getId().toString();
        return ResponseEntity.ok(bookingService.getBookingsByUserId(userId));
    }

    @DeleteMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingResponse> cancelBooking(
            @PathVariable String bookingId,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();

        String userId = user.getId().toString();

        BookingResponse response = bookingService.cancelBooking(
                bookingId,
                userId
        );

        return ResponseEntity.ok(response);
    }
}
