package com.example.cinra.domain.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "showtime_id", nullable = false)
    private Showtime showtime;

    @Column(name = "numOfTickets", nullable = false)
    private Integer numOfTickets;

    @Column(name = "booking_time", nullable = false)
    private LocalDateTime bookingTime;


    public Booking(User user, Integer numOfTickets, Showtime showtime) {
        this.user = user;
        this.numOfTickets = numOfTickets;
        this.bookingTime = LocalDateTime.now();
        this.showtime = showtime;
    }
}
