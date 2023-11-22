package com.example.cinra.data.repositories;

import com.example.cinra.domain.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
