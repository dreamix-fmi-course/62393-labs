package lab6.tickets.service;

import lab6.tickets.model.Event;
import lab6.tickets.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void createUser(User u);

    void deleteUser(UUID id);

    User findById(UUID id);

    void updateUserInformation(User user);

    /**
     * Return all visited events
     */
    List<Event> getAllVisitedEvent();

    /**
     * Return all visited events for the past month
     */
    List<Event> getAllVisitedEventsInPastMonth();

}