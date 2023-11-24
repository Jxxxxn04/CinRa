package com.example.cinra.data.service.user;

import com.example.cinra.data.repositories.UserRepository;
import com.example.cinra.data.responses.authorization.AuthorizeUserRequest;
import com.example.cinra.domain.entities.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        // TODO : Kontrollieren ob Username oder Email schon vergeben ist
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) throws EntityNotFoundException {

    }

    @Override
    public User getUser(Long id) throws EntityNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean authorizeUser(Long id, AuthorizeUserRequest authorizeUserRequest) throws EntityNotFoundException {
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
            User user = getUser(id);
            return encoder.matches(authorizeUserRequest.getPassword(), user.getPassword());
        } catch (EntityNotFoundException e) {
            return false;
        }
    }
}
