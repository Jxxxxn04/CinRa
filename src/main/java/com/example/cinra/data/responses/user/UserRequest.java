package com.example.cinra.data.responses.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    String username;
    String password;
    String email;
}
