package com.example.cinra.data.responses.showtime;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ShowtimeRequest {
    LocalDateTime startTime;
    Long theater_id;
    Long movie_id;
}
