package com.tickets.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tickets.config.AppConfig;
import com.tickets.model.Event;
import com.tickets.model.User;
import com.tickets.model.Ticket;
import com.tickets.repository.EventRepository;
import com.tickets.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

    private static final String NOT_FOUND_MESSAGE = "Ticket not found.";
    private static final String INVALID_DATA_MESSAGE = "Invalid data.";

    private TicketRepository ticketRepository;
    private EventRepository eventRepository;
    private AppConfig appConfig;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, EventRepository eventRepository, AppConfig appConfig) {
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
        this.appConfig = appConfig;
    }

    @Override
    public Ticket createTicket(Ticket ticket) throws Exception {
        int maxRow = appConfig.getEvent().getMaximumRow();
        int maxSeat = appConfig.getEvent().getMaximumSeat();

        if (ticket.getEvent().getDate().toLocalDate().compareTo(LocalDate.now()) < 0 ||
            ticket.getPrice().compareTo(BigDecimal.ZERO) < 0 ||
            this.ticketRepository.findAll().stream()
                    .filter(t -> t.getEvent().equals(ticket.getEvent()) && t.getRow() == ticket.getRow() && t.getSeat() == ticket.getSeat())
                    .filter(t -> t.getEvent().equals(ticket.getEvent()) && (t.getRow() > maxRow || t.getSeat() > maxSeat))
                    .count() > 0) {
            throw new IllegalArgumentException(INVALID_DATA_MESSAGE);
        }

        if (this.ticketRepository.existsById(ticket.getId())) {
            throw new IllegalArgumentException(String.format("%s already exists", ticket.getId()));
        }

        return this.ticketRepository.save(ticket);
    }

    @Override
    public void removeTicket(UUID id) {
        if (!this.ticketRepository.existsById(id)) {
            throw new NoSuchElementException(NOT_FOUND_MESSAGE);
        }
        this.ticketRepository.deleteById(id);
    }

    @Override
    public Ticket findById(UUID id) {
        return this.ticketRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_MESSAGE));
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        if (!this.ticketRepository.existsById(ticket.getId())) {
            throw new NoSuchElementException(NOT_FOUND_MESSAGE);
        }
        return this.ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getAllTickets() { 
        return this.ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findAllTicketsByUser(UUID userId) {
        return this.ticketRepository.findAllByUser(userId);
    }

    @Override
    public List<Ticket> findAllTicketsByEvent(UUID eventId) {
        return this.ticketRepository.findAllByEvent(eventId);
    }

    @Override
    public List<Event> getAllVisitedEvents() {
        return this.ticketRepository.findAllVisitedEvents();
    }

    @Override
    public List<Event> getAllVisitedEventsInPastMonth() {
        return this.ticketRepository.findAllVisitedEventsInPastMonth(LocalDate.now().minusMonths(1));
    }
}
