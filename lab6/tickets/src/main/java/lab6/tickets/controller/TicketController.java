package lab6.tickets.controller;

import lab6.tickets.logger.Logger;
import lab6.tickets.model.Ticket;
import lab6.tickets.model.User;
import lab6.tickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private Logger logger;

    @PostMapping(
            value = "create",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        try {
            this.ticketService.createTicket(ticket);
            logger.info("Created ticket " + ticket);

            return new ResponseEntity(
                    ticket,
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(
                    ticket,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Ticket> findById(@PathVariable UUID id) {
        return new ResponseEntity(
                this.ticketService.findById(id),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Ticket> deleteTicket(@PathVariable UUID id) {
        this.ticketService.removeTicket(id);

        return new ResponseEntity(
                "Deleted successfully",
                HttpStatus.OK
        );
    }
}
