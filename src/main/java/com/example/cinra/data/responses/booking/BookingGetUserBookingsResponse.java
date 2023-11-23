package com.example.cinra.data.responses.booking;

import com.example.cinra.domain.entities.User;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class BookingGetUserBookingsResponse {
    int statusCode = HttpStatus.OK.value();
    Long id;
    String username;
    GetAllBookingsResponse<BookingResponse> bookings;


    public BookingGetUserBookingsResponse(User user) {
        List<BookingResponse> bookingResponses = BookingResponse.convertBookingList(user.getBookings());
        this.bookings = new GetAllBookingsResponse<>(bookingResponses);
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
