package com.example.cinra.domain.entities;

import com.example.cinra.data.enums.Genre;
import com.example.cinra.data.responses.movie.MovieRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    private String title;
    private LocalDate releaseDate;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private Integer durationMinutes;
    private String pictureUrl;

    @OneToMany(mappedBy = "movie")
    private List<Showtime> showtimes;

    public Movie() {}

    public Movie(String title, LocalDate releaseDate, Genre genre, Integer durationMinutes, String pictureUrl) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.pictureUrl = pictureUrl;
    }

    public Movie(MovieRequest movieRequest) {
        this.title = movieRequest.getTitle();
        this.releaseDate = movieRequest.getReleaseDate();
        this.genre = movieRequest.getGenre();
        this.durationMinutes = movieRequest.getDurationMinutes();
        this.pictureUrl = movieRequest.getPictureUrl();
    }
}