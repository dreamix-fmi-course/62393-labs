package lab4.task1.service;

import lab4.task1.model.Event;
import lab4.task1.model.User;

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