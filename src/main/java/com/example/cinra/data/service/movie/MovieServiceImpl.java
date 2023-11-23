package com.example.cinra.data.service.movie;

import com.example.cinra.data.responses.movie.MovieRequest;
import com.example.cinra.data.repositories.MovieRepository;
import com.example.cinra.domain.entities.Movie;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieServiceImpl implements MovieService {

    MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie addMovie(MovieRequest movieRequest) {
        Movie movie = new Movie(movieRequest);
        movieRepository.save(movie);
        return movie;
    }

    @Override
    public Movie getMovie(Long id) throws EntityNotFoundException {
        return movieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Couldn't find Movie with ID: " + id));
    }

    @Override
    public void deleteMovie(Long id) throws EntityNotFoundException {

    }

    @Override
    public List<Movie> getAllMovies() {
        return null;
    }
}
