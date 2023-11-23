package com.example.cinra.domain.controller;

import com.example.cinra.Constants;
import com.example.cinra.data.enums.ResponseType;
import com.example.cinra.data.responses.GlobalGetListResponse;
import com.example.cinra.data.responses.errors.BadRequestErrorResponse;
import com.example.cinra.data.responses.errors.EmptyErrorResponse;
import com.example.cinra.data.responses.errors.NotFoundErrorResponse;
import com.example.cinra.data.responses.showtime.ShowTimeResponse;
import com.example.cinra.data.responses.theater.TheaterGetAllResponse;
import com.example.cinra.data.responses.theater.TheaterRequest;
import com.example.cinra.data.responses.theater.TheaterResponse;
import com.example.cinra.data.service.showtime.ShowtimeServiceImpl;
import com.example.cinra.data.service.theater.TheaterServiceImpl;
import com.example.cinra.domain.entities.Showtime;
import com.example.cinra.domain.entities.Theater;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = Constants.MAINPATH + "/theater")
public class TheaterController {

    TheaterServiceImpl theaterService;
    ShowtimeServiceImpl showtimeService;

    @Autowired
    public TheaterController(TheaterServiceImpl theaterService, ShowtimeServiceImpl showtimeService) {
        this.theaterService = theaterService;
        this.showtimeService = showtimeService;
    }

    @PostMapping
    ResponseEntity<?> addTheater(@RequestBody TheaterRequest theaterRequest) {
        EmptyErrorResponse<TheaterRequest> emptyErrorResponse = new EmptyErrorResponse<>();
        List<String> list = emptyErrorResponse.checkForEmptyValues(theaterRequest);
        if(!list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BadRequestErrorResponse(list));
        }

        theaterService.addTheater(theaterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Theater created");       // TODO : Bessere Response machen
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<?> getTheater(@PathVariable Long id) {
        try {
            Theater theater = theaterService.getTheater(id);
            return ResponseEntity.status(HttpStatus.OK).body(new TheaterResponse(theater));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundErrorResponse(e.getMessage()));
        }
    }

    @GetMapping(path = "/{id}/showtimes")
    ResponseEntity<?> getTheaterShowtimes(@PathVariable Long id) {
        try {
            Theater theater = theaterService.getTheater(id);
            List<ShowTimeResponse> showTimeResponses = new ArrayList<>();
            for(Showtime showtime : theater.getShowtimes()) {
                showTimeResponses.add(new ShowTimeResponse(showtime));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new GlobalGetListResponse<ShowTimeResponse>(showTimeResponses, ResponseType.SHOWTIME));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundErrorResponse(e.getMessage()));
        }
    }

    @GetMapping(path = "/{id}/showtimes/{showtimeID}")
    ResponseEntity<?> getTheaterShowtimes(@PathVariable Long id, @PathVariable Long showtimeID) {
        try {
            Theater theater = theaterService.getTheater(id);
            Showtime showtime = showtimeService.getShowtime(showtimeID);
            if(!theater.getShowtimes().contains(showtime)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundErrorResponse("Couldn't find showtime with ID: " + showtimeID + " for Theater with ID: " + id));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ShowTimeResponse(showtime));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundErrorResponse(e.getMessage()));
        }
    }

    @GetMapping()
    ResponseEntity<?> getAllTheater() {
        return ResponseEntity.status(HttpStatus.OK).body(new TheaterGetAllResponse(theaterService.getAllTheaters()));
    }
}