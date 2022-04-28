package com.tickets.service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tickets.model.Event;
import com.tickets.repository.EventRepository;
import com.tickets.logger.Logger;

@Service
public class EventServiceImpl implements EventService {

    private static final String NOT_FOUND_MESSAGE = "Event not found.";

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private Logger logger;

    @Override
    public Event createEvent(Event event) {
        if (this.eventRepository.existsById(event.getId())) {
            throw new IllegalArgumentException(String.format("%s already exists", event.getName()));
        }
        this.eventRepository.save(event);
        return event;
    }

    @Override
    public void removeEvent(UUID id) {
        if (!this.eventRepository.existsById(id)) {
            throw new NoSuchElementException(NOT_FOUND_MESSAGE);
        }
        this.eventRepository.deleteById(id);
    }

    @Override
    public Event findById(UUID id) {
        return this.eventRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_MESSAGE));
    }

    @Override
    public void updateEvent(Event event) {
        if (!this.eventRepository.existsById(event.getId())) {
            throw new NoSuchElementException(NOT_FOUND_MESSAGE);
        }
        this.eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return StreamSupport.stream(this.eventRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public List<Event> findAllEventsBetweenDates(LocalDate from, LocalDate to) {
        return StreamSupport.stream(this.eventRepository.findAll().spliterator(), false)
                .filter(t -> t.getDate().toLocalDate().compareTo(from) > 0 && t.getDate().toLocalDate().compareTo(to) < 0)
                .collect(Collectors.toList());
    }
}
