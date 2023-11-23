package com.example.cinra.domain.entities;

import com.example.cinra.data.models.Location;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "theaters")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id")
    private Long id;

    private String cinemaName;
    @Embedded
    private Location location;

    @OneToMany(mappedBy = "theater")
    private List<Showtime> showtimes;

    public Theater() {}

    public Theater(String cinemaName, Location location) {
        this.cinemaName = cinemaName;
        this.location = location;
    }
}
