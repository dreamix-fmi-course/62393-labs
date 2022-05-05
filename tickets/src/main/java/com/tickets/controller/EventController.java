package com.tickets.controller;

import com.tickets.dto.EventDto;
import com.tickets.dto.PerformerDto;
import com.tickets.logger.Logger;
import com.tickets.mapper.EventDtoMapper;
import com.tickets.mapper.PerformerDtoMapper;
import com.tickets.model.Event;
import com.tickets.model.Performer;
import com.tickets.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/events")
public class EventController {
    @Autowired
    private EventDtoMapper mapper;
    @Autowired
    private PerformerDtoMapper performerDtoMapper;
    @Autowired
    private EventService eventService;
    @Autowired
    private PerformerController performerService;
    @Autowired
    private Logger logger;

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto) {
        logger.info("Event to create: " + eventDto);
        Event event = this.mapper.convertToEntity(eventDto);
        event = this.eventService.createEvent(event);
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
    public ResponseEntity deleteEvent(@PathVariable UUID id) {
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
        List<EventDto> events = this.eventService.getAllEvents().stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
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
            event = eventService.updateEvent(event);
            logger.info("Updated event: " + event);

            return new ResponseEntity(
                    mapper.convertToDto(event),
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

    @GetMapping("/by-performer/{performerId}")
    public ResponseEntity<List<EventDto>> findAllEventsByPerformer(@PathVariable UUID performerId) {
        logger.info("Retrieving all events of performer with ID: " + performerId);
        List<EventDto> events = this.eventService.findAllEventsByPerformer(performerId).stream()
                .map(this.mapper::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity(
                events,
                HttpStatus.OK
        );
    }


    @PostMapping("{id}/performers")
    public ResponseEntity addPerformers(@PathVariable UUID id, @RequestBody List<PerformerDto> performerDtos) {
        try {
            Event event = this.eventService.findById(id);
            logger.info("Adding performers to event with ID: " + id);
            List<Performer> performers = performerDtos.stream()
                    .map(this.performerDtoMapper::convertToEntity)
                    .collect(Collectors.toList());
            this.eventService.addPerformers(event, performers);

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

    @GetMapping("template")
    public ResponseEntity<EventDto> getTemplate() {
        return new ResponseEntity(
                new EventDto(UUID.randomUUID(), "Name", LocalDateTime.now(), "Description"),
                HttpStatus.OK
        );
    }
}
