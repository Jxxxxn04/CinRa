package com.example.cinra.data.models;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    String username;
    String password;
    String email;
}
