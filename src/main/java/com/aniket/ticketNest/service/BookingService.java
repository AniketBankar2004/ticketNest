package com.aniket.ticketNest.service;

import com.aniket.ticketNest.dtos.BookingResponse;
import com.aniket.ticketNest.dtos.TicketResponse;
import com.aniket.ticketNest.exceptions.*;
import com.aniket.ticketNest.model.*;
import com.aniket.ticketNest.repository.BookingRepository;
import com.aniket.ticketNest.repository.ShowRepository;
import com.aniket.ticketNest.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final ShowRepository showRepository;
    private final TicketRepository ticketRepository;
    private final BookingRepository bookingRepository;

    @Transactional
    public BookingResponse bookTickets(
            String showId,
            List<String> seatNumbers,
            String userId
    ) {

        Show show = showRepository.findById(showId)
                .orElseThrow(() ->
                        new ShowNotFoundException("Show not found: " + showId)
                );

        // 1. First lock and validate all seats
        List<Ticket> tickets = new ArrayList<>();

        for (String seatNumber : seatNumbers) {

            Ticket ticket = ticketRepository.findForUpdate(showId, seatNumber)
                    .orElseThrow(() ->
                            new ShowNotFoundException(
                                    "Seat already booked: " + seatNumber
                            )
                    );

            if (ticket.getStatus() == TicketStatus.BOOKED) {
                throw new TicketAlreadyBookedException(
                        "Seat already booked: " + seatNumber
                );
            }

            tickets.add(ticket);
        }

        // 2. Now create the Booking
        Booking booking = Booking.builder()
                .userId(userId)
                .show(show)
                .bookingTime(LocalDateTime.now())
                .status(BookingStatus.CONFIRMED)
                .build();

        // 3. Attach booking to tickets
        for (Ticket ticket : tickets) {
            ticket.setStatus(TicketStatus.BOOKED);
            ticket.setBooking(booking);
        }

        // 4. Save booking FIRST
        Booking savedBooking = bookingRepository.save(booking);

        // 5. Then save tickets
        ticketRepository.saveAll(tickets);

        return BookingResponse.builder()
                .id(savedBooking.getId())
                .showId(showId)
                .userId(userId)
                .bookingTime(savedBooking.getBookingTime())
                .status(savedBooking.getStatus())
                .tickets(
                        tickets.stream()
                                .map(ticket -> TicketResponse.builder()
                                        .id(ticket.getId())
                                        .seatNumber(ticket.getSeatNumber())
                                        .price(ticket.getPrice())
                                        .status(ticket.getStatus())
                                        .build())
                                .toList()
                )
                .build();
    }

    @Transactional
    public BookingResponse cancelBooking(
            String bookingId,
            String userId
    ) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new BookingNotFoundException(
                                "Booking not found: " + bookingId
                        ));

        // Make sure the booking belongs to the logged-in user
        if (!booking.getUserId().equals(userId)) {
            throw new UnauthorizedException(
                    "You cannot cancel this booking"
            );
        }

        // Don't cancel an already cancelled booking
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new BookingAlreadyCancelledException(
                    "Booking is already cancelled"
            );
        }

        // Cancel all tickets associated with the booking
        for (Ticket ticket : booking.getTickets()) {
            ticket.setStatus(TicketStatus.AVAILABLE);
        }

        booking.setStatus(BookingStatus.CANCELLED);

        bookingRepository.save(booking);

        return BookingResponse.builder()
                .id(booking.getId())
                .showId(booking.getShow().getId())
                .userId(booking.getUserId())
                .bookingTime(booking.getBookingTime())
                .status(booking.getStatus())
                .build();
    }
}
