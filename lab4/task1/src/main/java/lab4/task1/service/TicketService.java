package lab4.task1.service;

import lab4.task1.model.Event;
import lab4.task1.model.Ticket;
import lab4.task1.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TicketService {

    /**
     * Create and validate ticket.
     * Can NOT create ticket in the past.
     * Can NOT create ticket with negative price.
     * Can NOT duplicate row and seat same event.
     * @param u
     */
    void createTicket(Ticket u) throws Exception;

    void removeTicket(UUID id);

    Ticket findById(UUID id);

    void updateTicket(Ticket ticket);

    /**
     * Return all tickets bought by a user
     * @param id
     * @return
     */
    List<Ticket> findAllTicketsByUser(User id);

    /**
     * Return all sold tickets by event
     * @param event
     * @return
     */
    List<Ticket> findAllTicketsByEvent(Event event);

    /**
     * Return all available events for between two dates
     * @param from
     * @param to
     * @return
     */
    List<Event> findAllEventsBetweenDates(LocalDate from, LocalDate to);
}