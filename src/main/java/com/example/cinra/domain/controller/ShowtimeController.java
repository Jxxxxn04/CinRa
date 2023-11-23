package com.example.cinra.domain.controller;


import com.example.cinra.Constants;
import com.example.cinra.data.responses.errors.BadRequestErrorResponse;
import com.example.cinra.data.responses.errors.EmptyErrorResponse;
import com.example.cinra.data.responses.showtime.ShowtimeRequest;
import com.example.cinra.data.service.showtime.ShowtimeServiceImpl;
import com.example.cinra.domain.entities.Showtime;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = Constants.MAINPATH + "/showtime")
public class ShowtimeController {
    ShowtimeServiceImpl showtimeService;
    @Autowired
    public ShowtimeController(ShowtimeServiceImpl showtimeService) {
        this.showtimeService = showtimeService;
    }

    @PostMapping
    ResponseEntity<?> addShowtime(@RequestBody ShowtimeRequest showtimeRequest) {
        EmptyErrorResponse<ShowtimeRequest> emptyErrorResponse = new EmptyErrorResponse<>();
        List<String> list = emptyErrorResponse.checkForEmptyValues(showtimeRequest);
        if(!list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BadRequestErrorResponse(list));
        }

        try {
            Showtime showtime = showtimeService.addShowtime(showtimeRequest);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());        // TODO : Bessere Response machen
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Showtime created");       // TODO : Bessere Response machen
    }

    @GetMapping
    ResponseEntity<?> getAllShowtimes() {
        return ResponseEntity.status(HttpStatus.OK).body(showtimeService.getAllShowtimes());
    }
}
