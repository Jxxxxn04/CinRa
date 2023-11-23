package com.example.cinra.data.service.showtime;

import com.example.cinra.data.responses.showtime.ShowtimeRequest;
import com.example.cinra.data.repositories.ShowtimeRepository;
import com.example.cinra.data.service.movie.MovieServiceImpl;
import com.example.cinra.data.service.theater.TheaterServiceImpl;
import com.example.cinra.domain.entities.Movie;
import com.example.cinra.domain.entities.Showtime;
import com.example.cinra.domain.entities.Theater;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowtimeServiceImpl implements ShowtimeService {

    ShowtimeRepository showtimeRepository;
    MovieServiceImpl movieService;
    TheaterServiceImpl theaterService;

    @Autowired
    public ShowtimeServiceImpl(ShowtimeRepository showtimeRepository, MovieServiceImpl movieService, TheaterServiceImpl theaterService) {
        this.showtimeRepository = showtimeRepository;
        this.movieService = movieService;
        this.theaterService = theaterService;
    }

    @Override
    public Showtime addShowtime(ShowtimeRequest showtimeRequest) throws EntityNotFoundException {
        try {
            Movie movie = movieService.getMovie(showtimeRequest.getMovie_id());
            Theater theater = theaterService.getTheater(showtimeRequest.getTheater_id());
            Showtime showtime = new Showtime(movie, theater, showtimeRequest.getStartTime());
            showtimeRepository.save(showtime);
            return showtime;
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e);
        }
    }

    @Override
    public Showtime getShowtime(Long id) throws EntityNotFoundException {
        return showtimeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Couldn't find Showtime with ID: " + id));
    }

    @Override
    public Showtime deleteShowtime(Long id) throws EntityNotFoundException {
        return null;
    }

    @Override
    public List<Showtime> getAllShowtimes() {
        return showtimeRepository.findAll();
    }
}
