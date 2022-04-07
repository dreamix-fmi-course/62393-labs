package lab6.tickets.service;

import lab6.tickets.model.Event;
import lab6.tickets.model.User;
import lab6.tickets.repository.TicketRepository;
import lab6.tickets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private TicketRepository ticketRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void createUser(User u) {
        this.userRepository.addUser(u);
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
    public List<Event> getAllVisitedEvent() {
        return this.ticketRepository.getAllTickets().stream()
                .map(t -> t.getEvent())
                .distinct()
                .filter(e -> e.getDate().compareTo(LocalDateTime.now()) < 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getAllVisitedEventsInPastMonth() {
        LocalDateTime now = LocalDateTime.now();
        return this.ticketRepository.getAllTickets().stream()
                .map(t -> t.getEvent())
                .distinct()
                .filter(e -> e.getDate().compareTo(now.minusMonths(1)) >= 0 && e.getDate().compareTo(now) < 0)
                .collect(Collectors.toList());
    }
}
