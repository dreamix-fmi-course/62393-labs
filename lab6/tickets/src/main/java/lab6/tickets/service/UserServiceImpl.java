package lab6.tickets.service;

import lab6.tickets.model.User;
import lab6.tickets.repository.TicketRepository;
import lab6.tickets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User u) {
        this.userRepository.addUser(u);
        return u;
    }

    @Override
    public void deleteUser(UUID id) {
        this.userRepository.deleteUser(id);
    }

    @Override
    public User findById(UUID id) {
        return this.userRepository.findById(id);
    }

    @Override
    public void updateUserInformation(User user) {
        this.userRepository.updateUser(user);
    }

    @Override
    public List<User> getAllUsers() { return this.userRepository.getAll(); }
}
