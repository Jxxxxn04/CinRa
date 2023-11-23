package com.example.cinra.data.responses.booking;

import lombok.Data;

@Data
public class BookingRequest {
    Long userID;
    Long showtimeID;
    Integer numOfTickets;
}
