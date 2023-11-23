package com.example.cinra.data.responses.booking;

import com.example.cinra.domain.entities.Booking;
import com.example.cinra.domain.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingUserResponse {

    private Long bookingID;
    private Integer numOfTickets;
    private LocalDateTime bookingTime;
    @JsonIgnore
    private User user;

    public BookingUserResponse(Booking booking) {
        this.bookingID = booking.getId();
        this.numOfTickets = booking.getNumOfTickets();
        this.bookingTime = booking.getBookingTime();
        this.user = booking.getUser();
    }
}
