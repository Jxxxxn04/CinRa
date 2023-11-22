package com.example.cinra.data.models.user;

import com.example.cinra.data.models.booking.BookingResponse;
import com.example.cinra.domain.entities.Booking;
import com.example.cinra.domain.entities.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponse {
    Long id;
    String username, email, password;
    LocalDateTime createdAt;
    List<BookingResponse> bookingResponses = new ArrayList<>();

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.createdAt = user.getCreatedAt();
        for(Booking booking : user.getBookings()) {
            bookingResponses.add(new BookingResponse(booking));
        }
    }
}
