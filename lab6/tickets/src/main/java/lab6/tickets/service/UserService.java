package lab6.tickets.service;

import lab6.tickets.model.Event;
import lab6.tickets.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User createUser(User u);

    void deleteUser(UUID id);

    User findById(UUID id);

    void updateUserInformation(User user);

    List<User> getAllUsers();

}