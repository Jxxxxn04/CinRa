package com.example.cinra.data.responses.booking;

import lombok.Data;

import java.util.List;

@Data
public class GetAllBookingsResponse<T> {
    int amountOfBookings;
    List<T> bookings;

    public GetAllBookingsResponse(List<T> bookingUserResponses) {
        this.bookings = bookingUserResponses;
        this.amountOfBookings = bookingUserResponses.size();
    }

}
