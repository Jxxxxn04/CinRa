package com.example.cinra.data.repositories;

import com.example.cinra.domain.entities.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
}
