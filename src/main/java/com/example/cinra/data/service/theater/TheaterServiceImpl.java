package com.example.cinra.data.service.theater;

import com.example.cinra.data.responses.theater.TheaterRequest;
import com.example.cinra.data.repositories.TheaterRepository;
import com.example.cinra.domain.entities.Theater;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterServiceImpl implements TheaterService {

    TheaterRepository theaterRepository;

    @Autowired
    public TheaterServiceImpl(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    @Override
    public Theater addTheater(TheaterRequest theaterRequest) {
        Theater theater = new Theater(theaterRequest.getCinemaName(), theaterRequest.getLocation());
        theaterRepository.save(theater);
        return theater;
    }

    @Override
    public Theater getTheater(Long id) throws EntityNotFoundException {
        return theaterRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Theater not found with ID: " + id));
    }

    @Override
    public void deleteTheater(Long id) throws EntityNotFoundException {

    }

    @Override
    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }
}
