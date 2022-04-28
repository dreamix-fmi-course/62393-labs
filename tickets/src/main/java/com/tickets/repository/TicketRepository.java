package com.tickets.repository;

import com.tickets.model.Event;
import com.tickets.model.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, UUID> {
    List<Ticket> findAll();

    @Query(value = "SELECT * FROM tickets\n" +
            "WHERE user_id = ?1", nativeQuery = true)
    List<Ticket> findAllByUser(UUID userId);

    @Query(value = "SELECT * FROM tickets\n" +
            "WHERE event_id = ?1", nativeQuery = true)
    List<Ticket> findAllByEvent(UUID eventId);

    @Query(value = "SELECT new com.tickets.model.Event(e.id, e.name, e.date, e.description) FROM Ticket t\n" +
            "RIGHT JOIN Event e ON t.event.id = e.id\n" +
            "WHERE e.date < CURRENT_DATE")
    List<Event> findAllVisitedEvents();

    @Query(value = "SELECT new com.tickets.model.Event(e.id, e.name, e.date, e.description) FROM Ticket t\n" +
            "RIGHT JOIN Event e ON t.event.id = e.id\n" +
            "WHERE e.date < CURRENT_DATE AND e.date > CURRENT_DATE - INTERVAL '1 MONTH'")
    List<Event> findAllVisitedEventsInPastMonth();
}
