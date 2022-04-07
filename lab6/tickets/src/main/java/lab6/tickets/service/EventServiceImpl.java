package lab6.tickets.service;

import lab6.tickets.logger.Logger;
import lab6.tickets.model.Event;
import lab6.tickets.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private Logger logger;

    @Override
    public void createEvent(Event u) {
        this.eventRepository.createEvent(u);
        this.logger.info("Created event " + u.getName());
        this.logger.debug("Created event " + u.getName());
    }

    @Override
    public void removeEvent(UUID id) {
        this.eventRepository.removeEvent(id);
    }

    @Override
    public Event findById(UUID id) {
        return this.eventRepository.findById(id);
    }

    @Override
    public void updateEvent(Event event) {
        this.eventRepository.updateEvent(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return this.eventRepository.getAllEvents();
    }
}
