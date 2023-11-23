package com.example.cinra.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "showtimes")
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showtime_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    private LocalDateTime startTime;

    @OneToMany(mappedBy = "showtime")
    private List<Booking> bookings;

    public Showtime(Movie movie, Theater theater, LocalDateTime startTime) {
        this.movie = movie;
        this.theater = theater;
        this.startTime = startTime;
    }

    public Showtime() {}
}
