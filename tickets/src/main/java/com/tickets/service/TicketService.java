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

    void updateTicket(Ticket ticket);

    List<Ticket> getAllTickets();

    List<Ticket> findAllTicketsByUser(User id);

    List<Ticket> findAllTicketsByEvent(Event event);

    List<Event> getAllVisitedEvents();

    List<Event> getAllVisitedEventsInPastMonth();
}
