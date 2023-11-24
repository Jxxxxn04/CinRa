package com.example.cinra.domain.controller;

import com.example.cinra.Constants;
import com.example.cinra.data.responses.authorization.AuthorizationInvalidResponse;
import com.example.cinra.data.responses.authorization.AuthorizationValidResponse;
import com.example.cinra.data.responses.authorization.AuthorizeUserRequest;
import com.example.cinra.data.responses.errors.BadRequestErrorResponse;
import com.example.cinra.data.responses.errors.EmptyErrorResponse;
import com.example.cinra.data.service.user.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = Constants.MAINPATH + "/authorization")
public class AuthorizationController {

    UserServiceImpl userService;

    @Autowired
    public AuthorizationController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<?> authorizeUser(@PathVariable Long id, @RequestBody AuthorizeUserRequest authorizeUserRequest) {
        EmptyErrorResponse<AuthorizeUserRequest> emptyErrorResponse = new EmptyErrorResponse<>();
        List<String> list = emptyErrorResponse.checkForEmptyValues(authorizeUserRequest);
        if(!list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BadRequestErrorResponse(list));
        }
        boolean isValid = userService.authorizeUser(id, authorizeUserRequest);
        try {
            if(!isValid) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AuthorizationInvalidResponse(authorizeUserRequest));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new AuthorizationValidResponse(userService.getUser(id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AuthorizationInvalidResponse(authorizeUserRequest));
        }
    }

}
