package com.example.cinra.domain.controller;

import com.example.cinra.Constants;
import com.example.cinra.data.responses.booking.*;
import com.example.cinra.data.responses.errors.BadRequestErrorResponse;
import com.example.cinra.data.responses.errors.EmptyErrorResponse;
import com.example.cinra.data.responses.errors.NotFoundErrorResponse;
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
        return ResponseEntity.status(HttpStatus.OK).body(new GetAllBookingsResponse<BookingUserResponse>(responses));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getBooking(@PathVariable Long id) {
        try {
            Booking booking = bookingService.getBooking(id);
            return ResponseEntity.status(HttpStatus.OK).body(new BookingUserResponse(booking));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundErrorResponse(e.getMessage()));
        }
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<?> getUserBookings(@PathVariable Long id) {
        try {
            User user = userService.getUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(new BookingGetUserBookingsResponse(user));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundErrorResponse(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> addBooking(@RequestBody BookingRequest bookingRequest) {
        EmptyErrorResponse<BookingRequest> emptyErrorResponse = new EmptyErrorResponse<>();
        List<String> list = emptyErrorResponse.checkForEmptyValues(bookingRequest);
        if(!list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BadRequestErrorResponse(list));
        }

        try {
            Booking booking = bookingService.addBooking(bookingRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(new BookingCreateResponse(booking));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundErrorResponse(e.getMessage()));
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        try {
            bookingService.deleteBooking(id);
            return ResponseEntity.status(HttpStatus.OK).body(new BookingDeleteResponse(HttpStatus.OK.value(), id, "Booking deleted successfully"));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundErrorResponse(e.getMessage()));
        }
    }


}
