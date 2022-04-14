package lab6.tickets.service;

import lab6.tickets.model.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface EventService {
    Event createEvent(Event u);

    void removeEvent(UUID id);

    Event findById(UUID id);

    void updateEvent(Event event);

    List<Event> getAllEvents();

    /**
     * Return all available events for between two dates
     * @param from
     * @param to
     * @return
     */
    List<Event> findAllEventsBetweenDates(LocalDate from, LocalDate to);
}