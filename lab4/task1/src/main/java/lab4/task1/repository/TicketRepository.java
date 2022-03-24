package lab4.task1.repository;

import lab4.task1.model.Ticket;

import java.util.List;
import java.util.UUID;

public interface TicketRepository {

    /**
     * Create ticket
     */
    void createTicket(Ticket u);

    /**
     * Remove ticket
     */
    void removeTicket(UUID id);

    /**
     * Find ticket by Id
     * @param id
     */
    Ticket findById(UUID id);

    /**
     * Update ticket information
     */
    void updateTicket(Ticket ticket);

    List<Ticket> getAllTickets();
}