package lab6.tickets.controller;

import lab6.tickets.logger.Logger;
import lab6.tickets.model.Event;
import lab6.tickets.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("events")
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private Logger logger;

    @PostMapping(
            value = "create",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        try {
            this.eventService.createEvent(event);
            logger.info("Created event " + event);

            return new ResponseEntity(
                    event,
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(
                    event,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Event> findById(@PathVariable UUID id) {
        return new ResponseEntity(
                this.eventService.findById(id),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable UUID id) {
        this.eventService.removeEvent(id);

        return new ResponseEntity(
                "Deleted successfully",
                HttpStatus.OK
        );
    }
}
