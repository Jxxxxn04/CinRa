package com.example.cinra.data.responses.booking;

import com.example.cinra.domain.entities.Booking;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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


    public static List<BookingResponse> convertBookingList(List<Booking> bookings) {
        List<BookingResponse> bookingResponses = new ArrayList<>();
        for(Booking booking : bookings) {
            bookingResponses.add(new BookingResponse(booking));
        }
        return bookingResponses;
    }
}
