package com.example.cinra.data.responses.authorization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AuthorizationInvalidResponse {
    int statusCode = HttpStatus.NOT_FOUND.value();
    String message = "Authorization failed due to wrong Password or Username";
    @JsonProperty("Given Credentials")
    AuthorizeUserRequest authorizeUserRequest;

    public AuthorizationInvalidResponse(AuthorizeUserRequest authorizeUserRequest) {
        this.authorizeUserRequest = authorizeUserRequest;
    }
}
