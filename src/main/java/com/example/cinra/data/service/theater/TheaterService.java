package com.example.cinra.data.service.theater;

import com.example.cinra.data.responses.theater.TheaterRequest;
import com.example.cinra.domain.entities.Theater;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface TheaterService {
    Theater addTheater(TheaterRequest theaterRequest);
    Theater getTheater(Long id) throws EntityNotFoundException;
    void deleteTheater(Long id) throws EntityNotFoundException;
    List<Theater> getAllTheaters();
}
