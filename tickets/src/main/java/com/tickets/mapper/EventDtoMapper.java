package com.tickets.mapper;

import com.tickets.model.Event;
import com.tickets.dto.EventDto;
import org.springframework.stereotype.Component;

@Component
public class EventDtoMapper {
    public Event convertToEntity(EventDto eventDto) {
        if (eventDto.getId() != null) {
            return new Event(eventDto.getId(), eventDto.getName(), eventDto.getDate(), eventDto.getDescription());
        }
        return new Event(eventDto.getName(), eventDto.getDate(), eventDto.getDescription());
    }

    public EventDto convertToDto(Event event) {
        return new EventDto(event.getId(), event.getName(), event.getDate(), event.getDescription());
    }
}
