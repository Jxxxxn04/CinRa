package com.example.cinra.data.responses.showtime;

import com.example.cinra.domain.entities.Booking;
import com.example.cinra.domain.entities.Showtime;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShowTimeResponse {
    private Long id;
    private LocalDateTime startTime;
    private List<ShowtimeAllBookingsResponse> bookings = new ArrayList<>();

    public ShowTimeResponse(Showtime showtime) {
        this.id = showtime.getId();
        this.startTime = showtime.getStartTime();
        for(Booking booking : showtime.getBookings()) {
            this.bookings.add(new ShowtimeAllBookingsResponse(booking));
        }
    }
}
