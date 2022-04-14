package lab6.tickets.service;

import lab6.tickets.logger.Logger;
import lab6.tickets.model.Event;
import lab6.tickets.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private Logger logger;

    @Override
    public Event createEvent(Event u) {
        this.eventRepository.createEvent(u);
        return u;
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

    @Override
    public List<Event> findAllEventsBetweenDates(LocalDate from, LocalDate to) {
        return this.eventRepository.getAllEvents().stream()
                .filter(t -> t.getDate().toLocalDate().compareTo(from) > 0 && t.getDate().toLocalDate().compareTo(to) < 0)
                .collect(Collectors.toList());
    }
}
