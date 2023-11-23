package com.example.cinra.data.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    String streetName;
    Integer streetNumber;
    String cityName;
    String country;
    Integer zipCode;
}
