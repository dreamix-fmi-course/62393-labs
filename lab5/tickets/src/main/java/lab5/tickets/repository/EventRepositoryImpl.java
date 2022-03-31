package lab5.tickets.repository;

import lab5.tickets.model.Event;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class EventRepositoryImpl implements EventRepository {
    Map<UUID, Event> events = new ConcurrentHashMap<>();

    @Override
    public void createEvent(Event event) {
        this.events.put(event.getEventId(), event);
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
        if (!this.events.containsKey(event.getEventId())) {
            throw new RuntimeException("Event with such id doesn't exist");
        }
        this.events.put(event.getEventId(), event);
    }

    @Override
    public List<Event> getAllEvents() {
        return this.events.values().stream().collect(Collectors.toList());
    }
}
