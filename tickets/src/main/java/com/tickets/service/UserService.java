package com.tickets.service;

import java.util.List;
import java.util.UUID;

import com.tickets.model.User;

public interface UserService {

    User createUser(User user);

    void deleteUser(UUID id);

    User findById(UUID id);

    User updateUserInformation(User user);

    List<User> getAllUsers();

}
