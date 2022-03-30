package lab4.task1.service;

import lab4.task1.model.Event;
import lab4.task1.repository.EventRepository;
import lab4.task1.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public void createEvent(Event u) {
        this.eventRepository.createEvent(u);
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
