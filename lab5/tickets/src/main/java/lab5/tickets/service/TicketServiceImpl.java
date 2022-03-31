package lab5.tickets.service;

import lab5.tickets.config.AppConfig;
import lab5.tickets.model.Event;
import lab5.tickets.model.Ticket;
import lab5.tickets.model.User;
import lab5.tickets.repository.EventRepository;
import lab5.tickets.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private AppConfig appConfig;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, EventRepository eventRepository, AppConfig appConfig) {
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
        this.appConfig = appConfig;
    }

    @Override
    public void createTicket(Ticket u) throws Exception {
        int maxRow = appConfig.getEventConfig().getMaximumRow();
        int maxSeat = appConfig.getEventConfig().getMaximumSeat();

        if (u.getEvent().getDate().toLocalDate().compareTo(LocalDate.now()) < 0 ||
            u.getPrice().compareTo(BigDecimal.ZERO) < 0 ||
            this.ticketRepository.getAllTickets().stream()
                    .filter(t -> t.getEvent().equals(u.getEvent()) && t.getRow() == u.getRow() && t.getSeat() == u.getSeat())
                    .filter(t -> t.getEvent().equals(u.getEvent()) && (t.getRow() > maxRow || t.getSeat() > maxSeat))
                    .count() > 0) {
            throw new Exception("Невалидни данни");
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
