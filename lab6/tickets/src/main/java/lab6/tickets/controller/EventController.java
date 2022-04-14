package lab6.tickets.controller;

import lab6.tickets.dto.EventDto;
import lab6.tickets.logger.Logger;
import lab6.tickets.mapper.EventDtoMapper;
import lab6.tickets.model.Event;
import lab6.tickets.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/events")
public class EventController {
    @Autowired
    private EventDtoMapper mapper;
    @Autowired
    private EventService eventService;
    @Autowired
    private Logger logger;

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto) {
        logger.info("Event to create: " + eventDto);
        Event event = this.mapper.convertToEntity(eventDto);
        this.eventService.createEvent(event);
        logger.info("Created event: " + event);

        return new ResponseEntity(
                this.mapper.convertToDto(event),
                HttpStatus.CREATED
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<EventDto> findById(@PathVariable UUID id) {
        try {
            logger.info("Event to retrieve with ID: " + id);
            EventDto eventDto = this.mapper.convertToDto(this.eventService.findById(id));
            logger.info("Retrieved event: " + eventDto);
            return new ResponseEntity(
                    eventDto,
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
    public ResponseEntity<Event> deleteEvent(@PathVariable UUID id) {
        try {
            logger.info("Event to delete with ID: " + id);
            this.eventService.removeEvent(id);
            logger.info("Deleted event with ID: " + id);
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
    public ResponseEntity<List<EventDto>> getAllEvents() {
        logger.info("Retrieving all events");
        List<Event> events = this.eventService.getAllEvents();
        return new ResponseEntity(
                events,
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<EventDto> updateEvent(@RequestBody EventDto eventDto) {
        try {
            logger.info("Event to update: " + eventDto);
            Event event = mapper.convertToEntity(eventDto);
            eventService.updateEvent(event);
            logger.info("Updated event: " + event);

            return new ResponseEntity(
                    eventDto,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("between")
    public ResponseEntity<List<EventDto>> findAllEventsBetweenDates(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to
    ) {
        logger.info("Event range to retrieve: from " + from + " to " + to);
        List<EventDto> events = this.eventService.findAllEventsBetweenDates(from, to).stream()
                .map(e -> this.mapper.convertToDto(e))
                .collect(Collectors.toList());
        logger.info("Retrieving events from " + from + " to " + to);

        return new ResponseEntity(
                events,
                HttpStatus.OK
        );
    }

    @GetMapping("template")
    public ResponseEntity<EventDto> getTemplate() {
        return new ResponseEntity(
                new EventDto(UUID.randomUUID(), "Name", LocalDateTime.now(), "Description"),
                HttpStatus.OK
        );
    }
}
