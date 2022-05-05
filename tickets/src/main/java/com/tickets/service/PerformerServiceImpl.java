package com.tickets.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.tickets.model.Event;
import com.tickets.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tickets.model.Performer;
import com.tickets.repository.PerformerRepository;

@Service
public class PerformerServiceImpl implements PerformerService {

    private static final String NOT_FOUND_MESSAGE = "Performer not found.";

    @Autowired
    private PerformerRepository performerRepository;
    @Autowired
    private EventRepository eventRepository;

    @Override
    public Performer createPerformer(Performer performer) {
        if (this.performerRepository.existsById(performer.getId())) {
            throw new IllegalArgumentException(String.format("%s already exists", performer.getName()));
        }
        return this.performerRepository.save(performer);
    }

    @Override
    public void deletePerformer(UUID id) {
        if (!this.performerRepository.existsById(id)) {
            throw new NoSuchElementException(NOT_FOUND_MESSAGE);
        }
        this.performerRepository.deleteById(id);
    }

    @Override
    public Performer findById(UUID id) {
        return this.performerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_MESSAGE));
    }

    @Override
    public Performer updatePerformerInformation(Performer performer) {
        if (!this.performerRepository.existsById(performer.getId())) {
            throw new NoSuchElementException(NOT_FOUND_MESSAGE);
        }
        return this.performerRepository.save(performer);
    }

    @Override
    public List<Performer> getAllPerformers() {
        return this.performerRepository.findAll();
    }

    @Override
    public List<Performer> getAllPerformersByEvent(UUID eventId) {
        return this.eventRepository.findById(eventId)
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_MESSAGE))
                .getPerformers().stream().collect(Collectors.toList());
    }

    @Override
    public void addEvents(Performer performer, List<Event> events) {
        performer.getEvents().addAll(events);
        this.performerRepository.save(performer);
    }
}
