package com.example.cinra.data.responses.booking;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingDeleteResponse {
    int statusCode;
    Long id;
    String message;
}
