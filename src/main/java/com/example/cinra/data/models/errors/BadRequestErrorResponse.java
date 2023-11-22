package com.example.cinra.data.models.errors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class BadRequestErrorResponse {
    int statusCode = HttpStatus.BAD_REQUEST.value();
    LocalDateTime timestamp = LocalDateTime.now();
    String message = "One or more Variables are missing!";
    List<String> missingArgs = new ArrayList<>();
    @JsonIgnore
    ObjectMapper objectMapper = new ObjectMapper();

    public BadRequestErrorResponse(List<String> missingArgs) {
        this.missingArgs.addAll(missingArgs);
    }
}
