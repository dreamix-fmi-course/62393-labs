package com.tickets.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            StreamSupport.stream(this.ticketRepository.findAll().spliterator(), false)
                    .filter(t -> t.getEvent().equals(ticket.getEvent()) && t.getRow() == ticket.getRow() && t.getSeat() == ticket.getSeat())
                    .filter(t -> t.getEvent().equals(ticket.getEvent()) && (t.getRow() > maxRow || t.getSeat() > maxSeat))
                    .count() > 0) {
            throw new IllegalArgumentException(INVALID_DATA_MESSAGE);
        }

        if (this.ticketRepository.existsById(ticket.getId())) {
            throw new IllegalArgumentException(String.format("%s already exists", ticket.getId()));
        }

        this.ticketRepository.save(ticket);
        return ticket;
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
    public void updateTicket(Ticket ticket) {
        if (!this.ticketRepository.existsById(ticket.getId())) {
            throw new NoSuchElementException(NOT_FOUND_MESSAGE);
        }
        this.ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getAllTickets() { 
        return StreamSupport.stream(this.ticketRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> findAllTicketsByUser(User user) {
        return StreamSupport.stream(this.ticketRepository.findAll().spliterator(), false)
                .filter(t -> t.getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> findAllTicketsByEvent(Event event) {
        return StreamSupport.stream(this.ticketRepository.findAll().spliterator(), false)
                .filter(t -> t.getEvent().getId().equals(event.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getAllVisitedEvents() {
        return StreamSupport.stream(this.ticketRepository.findAll().spliterator(), false)
                .map(t -> t.getEvent())
                .distinct()
                .filter(e -> e.getDate().compareTo(LocalDateTime.now()) < 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getAllVisitedEventsInPastMonth() {
        LocalDateTime now = LocalDateTime.now();
        return StreamSupport.stream(this.ticketRepository.findAll().spliterator(), false)
                .map(t -> t.getEvent())
                .distinct()
                .filter(e -> e.getDate().compareTo(now.minusMonths(1)) >= 0 && e.getDate().compareTo(now) < 0)
                .collect(Collectors.toList());
    }
}
