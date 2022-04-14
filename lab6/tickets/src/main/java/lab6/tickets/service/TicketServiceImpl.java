package lab6.tickets.service;

import lab6.tickets.config.AppConfig;
import lab6.tickets.model.Event;
import lab6.tickets.model.Ticket;
import lab6.tickets.model.User;
import lab6.tickets.repository.EventRepository;
import lab6.tickets.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public Ticket createTicket(Ticket u) throws Exception {
        int maxRow = appConfig.getEvent().getMaximumRow();
        int maxSeat = appConfig.getEvent().getMaximumSeat();

        if (u.getEvent().getDate().toLocalDate().compareTo(LocalDate.now()) < 0 ||
            u.getPrice().compareTo(BigDecimal.ZERO) < 0 ||
            this.ticketRepository.getAllTickets().stream()
                    .filter(t -> t.getEvent().equals(u.getEvent()) && t.getRow() == u.getRow() && t.getSeat() == u.getSeat())
                    .filter(t -> t.getEvent().equals(u.getEvent()) && (t.getRow() > maxRow || t.getSeat() > maxSeat))
                    .count() > 0) {
            throw new Exception("Invalid data");
        }

        this.ticketRepository.createTicket(u);
        return u;
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
    public List<Ticket> getAllTickets() { return this.ticketRepository.getAllTickets(); }

    @Override
    public List<Ticket> findAllTicketsByUser(User user) {
        return this.ticketRepository.getAllTickets().stream()
                .filter(t -> t.getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> findAllTicketsByEvent(Event event) {
        return this.ticketRepository.getAllTickets().stream()
                .filter(t -> t.getEvent().getId().equals(event.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getAllVisitedEvents() {
        return this.ticketRepository.getAllTickets().stream()
                .map(t -> t.getEvent())
                .distinct()
                .filter(e -> e.getDate().compareTo(LocalDateTime.now()) < 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getAllVisitedEventsInPastMonth() {
        LocalDateTime now = LocalDateTime.now();
        return this.ticketRepository.getAllTickets().stream()
                .map(t -> t.getEvent())
                .distinct()
                .filter(e -> e.getDate().compareTo(now.minusMonths(1)) >= 0 && e.getDate().compareTo(now) < 0)
                .collect(Collectors.toList());
    }
}
