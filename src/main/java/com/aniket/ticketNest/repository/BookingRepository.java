package com.aniket.ticketNest.repository;


import com.aniket.ticketNest.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,String> {
}
