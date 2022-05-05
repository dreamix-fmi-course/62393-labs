package com.tickets.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.tickets.model.Event;
import com.tickets.model.Performer;

public interface EventService {
    Event createEvent(Event event);

    void removeEvent(UUID id);

    Event findById(UUID id);

    Event updateEvent(Event event);

    List<Event> getAllEvents();

    List<Event> findAllEventsBetweenDates(LocalDate from, LocalDate to);

    List<Event> findAllEventsByPerformer(UUID performerId);

    void addPerformers(Event event, List<Performer> performers);
}
