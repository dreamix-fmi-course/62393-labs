package lab6.tickets.controller;

import lab6.tickets.dto.EventDto;
import lab6.tickets.dto.TicketDto;
import lab6.tickets.dto.UserDto;
import lab6.tickets.logger.Logger;
import lab6.tickets.mapper.EventDtoMapper;
import lab6.tickets.mapper.TicketDtoMapper;
import lab6.tickets.model.Event;
import lab6.tickets.model.Ticket;
import lab6.tickets.model.User;
import lab6.tickets.service.EventService;
import lab6.tickets.service.TicketService;
import lab6.tickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/tickets")
public class TicketController {

    @Autowired
    private TicketDtoMapper ticketMapper;
    @Autowired
    private EventDtoMapper eventMapper;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;
    @Autowired
    private Logger logger;

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) {
        try {
            logger.info("Ticket to create: " + ticketDto);
            Ticket ticket = ticketMapper.convertToEntity(ticketDto);
            this.ticketService.createTicket(ticket);
            logger.info("Created ticket: " + ticket);

            return new ResponseEntity(
                    ticketDto,
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(
                    "Invalid data",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<TicketDto> findById(@PathVariable UUID id) {
        try {
            logger.info("Ticket to retrieve with ID: " + id);
            TicketDto ticketDto = this.ticketMapper.convertToDto(this.ticketService.findById(id));
            logger.info("Retrieved ticket: " + ticketDto);
            return new ResponseEntity(
                    ticketDto,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TicketDto> deleteTicket(@PathVariable UUID id) {
        try {
            logger.info("Ticket to delete with ID: " + id);
            this.ticketService.removeTicket(id);
            logger.info("Deleted ticket with ID: " + id);
            return new ResponseEntity(
                    HttpStatus.OK
            );
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        logger.info("Retrieving all tickets");
        List<Ticket> tickets = this.ticketService.getAllTickets();
        return new ResponseEntity(
                tickets,
                HttpStatus.OK
        );
    }

    @GetMapping("by-user")
    public ResponseEntity<List<TicketDto>> findAllTicketsByUser(@PathVariable UUID id) {
        try {
            User user = this.userService.findById(id);
            logger.info("Retrieving all tickets for user: " + user);
            List<Ticket> tickets = this.ticketService.findAllTicketsByUser(user);
            return new ResponseEntity(
                    tickets,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("by-event")
    public ResponseEntity<List<TicketDto>> findAllTicketsByEvent(@PathVariable UUID id) {
        try {
            Event event = this.eventService.findById(id);
            logger.info("Retrieving all tickets for event: " + event);
            List<Ticket> tickets = this.ticketService.findAllTicketsByEvent(event);
            return new ResponseEntity(
                    tickets,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("visited-events")
    public ResponseEntity<List<EventDto>> getAllVisitedEvents() {
        logger.info("Retrieving all visited events");
        List<EventDto> events = this.ticketService.getAllVisitedEvents().stream()
                .map(e -> eventMapper.convertToDto(e))
                .collect(Collectors.toList());
        return new ResponseEntity(
                events,
                HttpStatus.OK
        );
    }

    @GetMapping("visited-events/past-month")
    public ResponseEntity<List<EventDto>> getAllVisitedEventsInPastMonth() {
        logger.info("Retrieving all visited events");
        List<EventDto> events = this.ticketService.getAllVisitedEventsInPastMonth().stream()
                .map(e -> eventMapper.convertToDto(e))
                .collect(Collectors.toList());
        return new ResponseEntity(
                events,
                HttpStatus.OK
        );
    }

    @GetMapping("template")
    public ResponseEntity<TicketDto> getTemplate() {
        return new ResponseEntity(
                new TicketDto(UUID.randomUUID(), new BigDecimal(0.99), 0, 0,
                        new UserDto(UUID.randomUUID(), "UserName", "Email@mail.org"),
                        new EventDto(UUID.randomUUID(), "Name", LocalDateTime.now(), "Description")),
                HttpStatus.OK
        );
    }
}
