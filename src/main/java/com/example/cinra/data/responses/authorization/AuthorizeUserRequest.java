package com.example.cinra.data.responses.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorizeUserRequest {
    String email;
    String password;
}
