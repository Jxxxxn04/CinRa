package com.example.cinra.data.service.user;

import com.example.cinra.data.responses.authorization.AuthorizeUserRequest;
import com.example.cinra.domain.entities.User;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface UserService {
    User addUser(User user);
    void deleteUser(Long id) throws EntityNotFoundException;
    User getUser(Long id) throws EntityNotFoundException;
    List<User> getAllUsers();
    boolean authorizeUser(Long id, AuthorizeUserRequest authorizeUserRequest) throws EntityNotFoundException;
}
