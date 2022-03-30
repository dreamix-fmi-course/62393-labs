package lab4.task1.service;

import lab4.task1.model.Event;
import lab4.task1.model.Ticket;
import lab4.task1.model.User;
import lab4.task1.repository.EventRepository;
import lab4.task1.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;
    private EventRepository eventRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, EventRepository eventRepository) {
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void createTicket(Ticket u) throws Exception {
        if (u.getEvent().getDate().toLocalDate().compareTo(LocalDate.now()) < 0 ||
            u.getPrice().compareTo(BigDecimal.ZERO) < 0 ||
            this.ticketRepository.getAllTickets().stream()
                    .filter(t -> t.getEvent().equals(u.getEvent()) && t.getRow() == u.getRow() && t.getSeat() == u.getSeat())
                    .count() > 0) {
            throw new Exception();
        }

        this.ticketRepository.createTicket(u);
    }

    @Override
    public void removeTicket(UUID id) {
        this.ticketRepository.removeTicket(id);
    }

    @Override
    public Ticket findById(UUID id) {
        return this.ticketRepository.findById(id);
    }

    @Override
    public void updateTicket(Ticket ticket) {
        this.ticketRepository.updateTicket(ticket);
    }

    @Override
    public List<Ticket> findAllTicketsByUser(User user) {
        return this.ticketRepository.getAllTickets().stream()
                .filter(t -> t.getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> findAllTicketsByEvent(Event event) {
        return this.ticketRepository.getAllTickets().stream()
                .filter(t -> t.getEvent().getEventId().equals(event.getEventId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> findAllEventsBetweenDates(LocalDate from, LocalDate to) {
        return this.eventRepository.getAllEvents().stream()
                .filter(t -> t.getDate().toLocalDate().compareTo(from) > 0 && t.getDate().toLocalDate().compareTo(to) < 0)
                .collect(Collectors.toList());
    }
}
