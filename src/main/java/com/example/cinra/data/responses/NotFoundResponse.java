package com.example.cinra.data.responses;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class NotFoundResponse {
    LocalDateTime timestamp = LocalDateTime.now();
    int statusCode = HttpStatus.NOT_FOUND.value();
    String method;
    String error = "Path not Found";
    String path;

    public NotFoundResponse(String path, String methodName) {
        this.path = path;
        this.method = methodName;
    }
}
