package com.example.cinra.data.models.errors;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class NotFoundErrorResponse {
    int statusCode = HttpStatus.NOT_FOUND.value();
    LocalDateTime timestamp = LocalDateTime.now();
    String message;

    public NotFoundErrorResponse(String message) {
        this.message = message;
    }
}
