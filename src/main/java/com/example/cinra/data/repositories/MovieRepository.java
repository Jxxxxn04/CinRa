package com.example.cinra.data.repositories;

import com.example.cinra.domain.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
