package com.example.cinra.domain.controller;

import com.example.cinra.data.responses.NotFoundResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/**")
public class DefaultController {

    @RequestMapping(method = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<NotFoundResponse> handleDefault(HttpServletRequest request) {
        String requestedPath = request.getRequestURI();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundResponse(requestedPath, request.getMethod()));
    }
}