package com.example.cinra.data.responses.showtime;

import com.example.cinra.domain.entities.Booking;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowtimeAllBookingsResponse {

    Long id;
    Long ownerID;
    String ownerUsername;
    Integer numOfTickets;
    LocalDateTime bookingTime;

    public ShowtimeAllBookingsResponse(Booking booking) {
        this.id = booking.getId();
        this.ownerID = booking.getUser().getId();
        this.ownerUsername = booking.getUser().getUsername();
        this.numOfTickets = booking.getNumOfTickets();
        this.bookingTime = booking.getBookingTime();
    }
}
