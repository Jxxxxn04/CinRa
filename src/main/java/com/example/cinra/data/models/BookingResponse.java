package com.example.cinra.data.models;

import com.example.cinra.domain.entities.Booking;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingResponse {

    private Long bookingID;
    private Integer numOfTickets;
    private LocalDateTime bookingTime;

    public BookingResponse(Booking booking) {
        this.bookingID = booking.getId();
        this.numOfTickets = booking.getNumOfTickets();
        this.bookingTime = booking.getBookingTime();
    }
}
