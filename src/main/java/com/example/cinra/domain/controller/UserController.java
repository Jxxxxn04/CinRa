package com.example.cinra.domain.controller;

import com.example.cinra.Constants;
import com.example.cinra.data.models.errors.EmptyErrorResponse;
import com.example.cinra.data.models.errors.NotFoundErrorResponse;
import com.example.cinra.data.models.user.UserRequest;
import com.example.cinra.data.models.user.UserResponse;
import com.example.cinra.data.service.booking.BookingServiceImpl;
import com.example.cinra.data.service.user.UserServiceImpl;
import com.example.cinra.domain.entities.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = Constants.MAINPATH + "/user")
public class UserController {

    UserServiceImpl userService;
    BookingServiceImpl bookingService;

    @Autowired
    public UserController(UserServiceImpl userService, BookingServiceImpl bookingService) {
        this.userService = userService;
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserResponse> responses = new ArrayList<>();
        for(User user : userService.getAllUsers()) {
            responses.add(new UserResponse(user));
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        try {
            User user = userService.getUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(new UserResponse(user));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundErrorResponse(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        EmptyErrorResponse<UserRequest> emptyErrorResponse = new EmptyErrorResponse<>();
        List<String> list = emptyErrorResponse.checkForEmptyValues(userRequest);
        if(!list.isEmpty()) {
            String errorMessage = "Empty values in fields: " + String.join(", ", list);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        User user = new User(userRequest.getUsername(), userRequest.getPassword(), userRequest.getEmail());
        User returnUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
