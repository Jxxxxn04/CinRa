package com.example.cinra.data.repositories;

import com.example.cinra.domain.entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
}
