package lab6.tickets.repository;

import lab6.tickets.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class TicketRepositoryImpl implements TicketRepository {
    Map<UUID, Ticket> tickets = new ConcurrentHashMap<>();

    @Override
    public void createTicket(Ticket u) {
        this.tickets.put(u.getTicketId(), u);
    }

    @Override
    public void removeTicket(UUID id) {
        this.tickets.remove(id);
    }

    @Override
    public Ticket findById(UUID id) {
        return this.tickets.getOrDefault(id, null);
    }

    @Override
    public void updateTicket(Ticket ticket) {
        if (!this.tickets.containsKey(ticket.getTicketId())) {
            throw new RuntimeException("Ticket with such id doesn't exist");
        }
        this.tickets.replace(ticket.getTicketId(), ticket);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return this.tickets.values().stream().collect(Collectors.toList());
    }
}
