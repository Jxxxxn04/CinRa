package com.example.cinra.data.responses.theater;

import com.example.cinra.data.models.Location;
import com.example.cinra.data.responses.showtime.ShowTimeResponse;
import com.example.cinra.domain.entities.Showtime;
import com.example.cinra.domain.entities.Theater;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TheaterResponse {
    private Long id;
    private String cinemaName;
    private Location location;
    private List<ShowTimeResponse> showtimes = new ArrayList<>();

    public TheaterResponse(Theater theater) {
        this.id = theater.getId();
        this.cinemaName = theater.getCinemaName();
        this.location = theater.getLocation();
        for(Showtime showtime : theater.getShowtimes()) {
            this.showtimes.add(new ShowTimeResponse(showtime));
        }
    }
}
