package com.tickets.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tickets.model.User;
import com.tickets.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private static final String NOT_FOUND_MESSAGE = "User not found.";

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        if (this.userRepository.existsById(user.getId())) {
            throw new IllegalArgumentException(String.format("%s already exists", user.getUsername()));
        }
        return this.userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID id) {
        if (!this.userRepository.existsById(id)) {
            throw new NoSuchElementException(NOT_FOUND_MESSAGE);
        }
        this.userRepository.deleteById(id);
    }

    @Override
    public User findById(UUID id) {
        return this.userRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_MESSAGE));
    }

    @Override
    public User updateUserInformation(User user) {
        if (!this.userRepository.existsById(user.getId())) {
            throw new NoSuchElementException(NOT_FOUND_MESSAGE);
        }
        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
