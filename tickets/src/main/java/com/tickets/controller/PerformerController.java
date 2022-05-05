package com.tickets.controller;

import com.tickets.dto.EventDto;
import com.tickets.dto.PerformerDto;
import com.tickets.logger.Logger;
import com.tickets.mapper.EventDtoMapper;
import com.tickets.mapper.PerformerDtoMapper;
import com.tickets.model.Event;
import com.tickets.model.Performer;
import com.tickets.service.EventService;
import com.tickets.service.PerformerService;
import org.aspectj.weaver.patterns.PerObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/performers")
public class PerformerController {

    @Autowired
    private PerformerDtoMapper mapper;
    @Autowired
    private EventDtoMapper eventDtoMapper;
    @Autowired
    private PerformerService performerService;
    @Autowired
    private EventService eventService;
    @Autowired
    private Logger logger;

    @PostMapping
    public ResponseEntity<PerformerDto> createPerformer(@RequestBody PerformerDto performerDto) {
        try {
            logger.info("Performer to create: " + performerDto);
            Performer performer = this.mapper.convertToEntity(performerDto);
            performer = this.performerService.createPerformer(performer);
            logger.info("Created performer: " + performer);

            return new ResponseEntity(
                    this.mapper.convertToDto(performer),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<PerformerDto> findById(@PathVariable UUID id) {
        try {
            logger.info("Performer to retrieve with ID: " + id);
            PerformerDto performerDto = this.mapper.convertToDto(this.performerService.findById(id));
            logger.info("Retrieved performer: " + performerDto);
            return new ResponseEntity(
                    performerDto,
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
    public ResponseEntity<PerformerDto> deletePerformer(@PathVariable UUID id) {
        try {
            logger.info("Performer to delete with ID: " + id);
            this.performerService.deletePerformer(id);
            logger.info("Deleted performer with ID: " + id);
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

    @PutMapping
    public ResponseEntity<PerformerDto> updatePerformer(@RequestBody PerformerDto performerDto) {
        try {
            logger.info("Performer to update: " + performerDto);
            Performer performer = mapper.convertToEntity(performerDto);
            performer = performerService.updatePerformerInformation(performer);
            logger.info("Updated performer: " + performer);

            return new ResponseEntity(
                    this.mapper.convertToDto(performer),
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
    public ResponseEntity<List<PerformerDto>> getAllPerformers() {
        logger.info("Retrieving all performers");
        List<PerformerDto> performers = this.performerService.getAllPerformers().stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity(
                performers,
                HttpStatus.OK
        );
    }

    @GetMapping("by-event/{eventId}")
    public ResponseEntity<List<PerformerDto>> getAllPerformersByEvent(@PathVariable UUID eventId) {
        logger.info("Retrieving all performers of event with ID: " + eventId);
        List<PerformerDto> performers = this.performerService.getAllPerformersByEvent(eventId).stream()
                .map(this.mapper::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity(
                performers,
                HttpStatus.OK
        );
    }

    @PostMapping("{id}/events")
    public ResponseEntity addEvents(@PathVariable UUID id, @RequestBody List<EventDto> eventDtos) {
        try {
            Performer performer = this.performerService.findById(id);
            logger.info("Adding events to performer with ID: " + id);
            List<Event> events = eventDtos.stream()
                    .map(this.eventDtoMapper::convertToEntity)
                    .collect(Collectors.toList());
            this.performerService.addEvents(performer, events);

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
    public ResponseEntity<PerformerDto> getTemplate() {
        return new ResponseEntity(
                new PerformerDto(UUID.randomUUID(), "PerformerName", "StageName", 20),
                HttpStatus.OK
        );
    }
}
