package com.example.cinra.data.service.booking;

import com.example.cinra.data.models.BookingRequest;
import com.example.cinra.domain.entities.Booking;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface BookingService {
    void addBooking(BookingRequest bookingRequest);
    void deleteBooking(Long id) throws EntityNotFoundException;
    Booking getBooking(Long id) throws EntityNotFoundException;
    List<Booking> getAllBookings();
}
