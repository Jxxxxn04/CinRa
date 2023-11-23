package com.example.cinra.data.service.movie;

import com.example.cinra.data.responses.movie.MovieRequest;
import com.example.cinra.domain.entities.Movie;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface MovieService {

    Movie addMovie(MovieRequest movieRequest);
    Movie getMovie(Long id) throws EntityNotFoundException;
    void deleteMovie(Long id) throws EntityNotFoundException;
    List<Movie> getAllMovies();
}
