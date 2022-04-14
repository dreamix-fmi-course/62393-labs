package lab6.tickets.mapper;

import lab6.tickets.model.Event;
import lab6.tickets.dto.EventDto;
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
