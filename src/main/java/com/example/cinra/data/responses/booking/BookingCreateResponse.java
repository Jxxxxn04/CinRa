package com.example.cinra.data.responses.booking;

import com.example.cinra.domain.entities.Booking;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BookingCreateResponse {
    int statusCode = HttpStatus.CREATED.value();
    String message = "Booking successfully added!";
    @JsonProperty("booking")
    BookingResponse bookingResponse;
    AddedTo addedTo;

    public  BookingCreateResponse(Booking booking) {
        this.bookingResponse = new BookingResponse(booking);
        this.addedTo = new AddedTo(booking.getUser().getId(), booking.getUser().getUsername());
    }

    @Data
    private static class AddedTo {
        private Long id;
        private String username;

        public AddedTo(Long id, String username) {
            this.id = id;
            this.username = username;
        }
    }
}
