package com.tickets.service;

import java.util.List;
import java.util.UUID;

import com.tickets.model.Event;
import com.tickets.model.Performer;

public interface PerformerService {

    Performer createPerformer(Performer performer);

    void deletePerformer(UUID id);

    Performer findById(UUID id);

    Performer updatePerformerInformation(Performer performer);

    List<Performer> getAllPerformers();

    List<Performer> getAllPerformersByEvent(UUID eventId);

    void addEvents(Performer performer, List<Event> events);
}
