package com.example.cinra.data.service.user;

import com.example.cinra.data.repositories.UserRepository;
import com.example.cinra.domain.entities.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
}
