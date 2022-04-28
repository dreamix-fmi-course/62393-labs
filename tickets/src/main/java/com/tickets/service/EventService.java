package com.tickets.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.tickets.model.Event;

public interface EventService {
    Event createEvent(Event event);

    void removeEvent(UUID id);

    Event findById(UUID id);

    void updateEvent(Event event);

    List<Event> getAllEvents();

    List<Event> findAllEventsBetweenDates(LocalDate from, LocalDate to);
}
