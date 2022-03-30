package lab4.task1.repository;

import lab4.task1.model.User;

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
