package lab5.tickets.repository;

import lab5.tickets.model.User;

import java.util.UUID;

public interface UserRepository {

    /**
     * Create new user
     */
    void addUser(User u);

    /**
     * Delete user
     */
    void deleteUser(UUID id);

    /**
     * Find user by id
     */
    User findById(UUID id);

    /**
     * Update user information
     */
    void updateUser(User user);

}
