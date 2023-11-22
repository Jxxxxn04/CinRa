package com.example.cinra.domain.controller;

import com.example.cinra.Constants;
import com.example.cinra.data.models.BookingRequest;
import com.example.cinra.data.models.BookingUserResponse;
import com.example.cinra.data.models.EmptyErrorResponse;
import com.example.cinra.data.models.UserResponse;
import com.example.cinra.data.service.booking.BookingServiceImpl;
import com.example.cinra.data.service.user.UserServiceImpl;
import com.example.cinra.domain.entities.Booking;
import com.example.cinra.domain.entities.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = Constants.MAINPATH + "/booking")
public class BookingController {

    UserServiceImpl userService;
    BookingServiceImpl bookingService;

    @Autowired
    public BookingController(UserServiceImpl userService, BookingServiceImpl bookingService) {
        this.userService = userService;
        this.bookingService = bookingService;
    }


    @GetMapping
    public ResponseEntity<?> getAllBookings() {
        List<BookingUserResponse> responses = new ArrayList<>();
        for(Booking booking : bookingService.getAllBookings()) {
            responses.add(new BookingUserResponse(booking));
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getBooking(@PathVariable Long id) {
        try {
            Booking booking = bookingService.getBooking(id);
            return ResponseEntity.status(HttpStatus.OK).body(new BookingUserResponse(booking));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());     // TODO : Bessere Error Message machen
        }
    }

    @PostMapping
    public ResponseEntity<?> addBooking(@RequestBody BookingRequest bookingRequest) {
        EmptyErrorResponse<BookingRequest> emptyErrorResponse = new EmptyErrorResponse<>();
        List<String> list = emptyErrorResponse.checkForEmptyValues(bookingRequest);
        if(!list.isEmpty()) {
            String errorMessage = "Empty values in fields: " + String.join(", ", list);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        try {
            bookingService.addBooking(bookingRequest);
            return ResponseEntity.status(HttpStatus.OK).body("Booking successfully added!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());     // TODO : Bessere Error Message machen
        }
    }


}
