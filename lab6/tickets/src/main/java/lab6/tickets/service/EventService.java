package lab6.tickets.service;

import lab6.tickets.model.Event;

import java.util.List;
import java.util.UUID;

public interface EventService {
    void createEvent(Event u);

    void removeEvent(UUID id);

    Event findById(UUID id);

    void updateEvent(Event event);

    List<Event> getAllEvents();
}