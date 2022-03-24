package lab4.task1.repository;

import lab4.task1.model.Event;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class EventDB implements EventRepository {
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
        this.events.replace(event.getEventId(), event);
    }

    @Override
    public List<Event> getAllEvents() {
        return this.events.values().stream().collect(Collectors.toList());
    }
}
