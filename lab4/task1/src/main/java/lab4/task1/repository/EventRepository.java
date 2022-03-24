package lab4.task1.repository;

import lab4.task1.model.Event;

import java.util.List;
import java.util.UUID;

public interface EventRepository {

    /***
     * Create event
     * @param event
     */
    void createEvent(Event event);

    /**
     * Remove event
     * @param id
     */
    void removeEvent(UUID id);

    /**
     * Find event by id
     * @param id of the event
     * @return
     */
    Event findById(UUID id);


    /**
     * Update event information
     * @param event
     */
    void updateEvent(Event event);

    /**
     * Get all events
     */
    List<Event> getAllEvents();
}
