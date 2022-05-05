package com.tickets.service;

import com.tickets.model.Event;
import com.tickets.model.Ticket;
import com.tickets.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TicketService {

    Ticket createTicket(Ticket ticket) throws Exception;

    void removeTicket(UUID id);

    Ticket findById(UUID id);

    Ticket updateTicket(Ticket ticket);

    List<Ticket> getAllTickets();

    List<Ticket> findAllTicketsByUser(UUID userId);

    List<Ticket> findAllTicketsByEvent(UUID eventId);

    List<Event> getAllVisitedEvents();

    List<Event> getAllVisitedEventsInPastMonth();
}
