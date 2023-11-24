package com.example.cinra.data.responses.authorization;

import com.example.cinra.data.responses.user.UserResponse;
import com.example.cinra.domain.entities.User;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AuthorizationValidResponse {
    int statusCode = HttpStatus.OK.value();
    String message = "Validation valid";
    UserResponse userResponse;

    public AuthorizationValidResponse(User user) {
        this.userResponse = new UserResponse(user);
    }
}
