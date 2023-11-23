package com.example.cinra.data.responses.theater;

import com.example.cinra.domain.entities.Theater;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TheaterGetAllResponse {
    Integer numOfTheaters;
    List<TheaterResponse> theater = new ArrayList<>();

    public TheaterGetAllResponse(List<Theater> theaters) {
        numOfTheaters = theaters.size();
        theaters.forEach(theaterObject -> {
            this.theater.add(new TheaterResponse(theaterObject));
        });
    }
}
