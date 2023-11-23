package com.example.cinra.data.responses.theater;

import com.example.cinra.data.models.Location;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TheaterRequest {
    String cinemaName;
    Location location;
}
