package lab6.tickets.repository;

import lab6.tickets.model.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class EventRepositoryImpl implements EventRepository {
    Map<UUID, Event> events = new ConcurrentHashMap<>();

    @Override
    public void createEvent(Event event) {
        this.events.put(event.getId(), event);
    }

    @Override
    public void removeEvent(UUID id) {
        this.events.remove(id);
    }

    @Override
    public Event findById(UUID id) {
        return this.events.getOrDefault(id, null);
    }

    @Override
    public void updateEvent(Event event) {
        if (!this.events.containsKey(event.getId())) {
            throw new RuntimeException("Event with id " + event.getId() + " doesn't exist");
        }
        this.events.put(event.getId(), event);
    }

    @Override
    public List<Event> getAllEvents() {
        return new ArrayList<>(this.events.values());
    }
}
