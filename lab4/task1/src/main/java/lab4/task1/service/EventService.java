package lab4.task1.service;

import lab4.task1.model.Event;

import java.util.List;
import java.util.UUID;

public interface EventService {
    void createEvent(Event u);

    void removeEvent(UUID id);

    Event findById(UUID id);

    void updateEvent(Event event);

    List<Event> getAllEvents();
}