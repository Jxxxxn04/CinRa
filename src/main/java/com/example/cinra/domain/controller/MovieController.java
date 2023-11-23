package com.example.cinra.domain.controller;

import com.example.cinra.Constants;
import com.example.cinra.data.responses.errors.BadRequestErrorResponse;
import com.example.cinra.data.responses.errors.EmptyErrorResponse;
import com.example.cinra.data.responses.movie.MovieRequest;
import com.example.cinra.data.service.movie.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = Constants.MAINPATH + "/movie")
public class MovieController {

    MovieServiceImpl movieService;

    @Autowired
    public MovieController(MovieServiceImpl movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    ResponseEntity<?> addTheater(@RequestBody MovieRequest movieRequest) {
        EmptyErrorResponse<MovieRequest> emptyErrorResponse = new EmptyErrorResponse<>();
        List<String> list = emptyErrorResponse.checkForEmptyValues(movieRequest);
        if(!list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BadRequestErrorResponse(list));
        }

        movieService.addMovie(movieRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Movie created");       // TODO : Bessere Response machen
    }
}
