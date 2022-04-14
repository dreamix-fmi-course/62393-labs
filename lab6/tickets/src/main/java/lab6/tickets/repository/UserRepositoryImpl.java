package lab6.tickets.repository;

import lab6.tickets.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepositoryImpl implements UserRepository {
    Map<UUID, User> users = new ConcurrentHashMap<>();

    @Override
    public void addUser(User u) {
        this.users.put(u.getId(), u);
    }

    @Override
    public void deleteUser(UUID id) {
        this.users.remove(id);
    }

    @Override
    public User findById(UUID id) {
        return this.users.getOrDefault(id, null);
    }

    @Override
    public void updateUser(User user) {
        if (!this.users.containsKey(user.getId())) {
            throw new RuntimeException("User with such id doesn't exist");
        }
        this.users.replace(user.getId(), user);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(this.users.values());
    }
}
