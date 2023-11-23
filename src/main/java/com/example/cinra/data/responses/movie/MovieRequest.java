package com.example.cinra.data.responses.movie;

import com.example.cinra.data.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class MovieRequest {
    String title;
    LocalDate releaseDate;
    Genre genre;
    Integer durationMinutes;
    String pictureUrl;
}
