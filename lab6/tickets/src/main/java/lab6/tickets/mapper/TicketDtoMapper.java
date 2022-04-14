package lab6.tickets.mapper;

import lab6.tickets.dto.TicketDto;
import lab6.tickets.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketDtoMapper {
    @Autowired
    private UserDtoMapper userMapper;
    @Autowired
    private EventDtoMapper eventMapper;

    public Ticket convertToEntity(TicketDto ticketDto) {
        if (ticketDto.getId() != null) {
            return new Ticket(
                    ticketDto.getId(),
                    ticketDto.getPrice(),
                    ticketDto.getRow(),
                    ticketDto.getSeat(),
                    userMapper.convertToEntity(ticketDto.getUserDto()),
                    eventMapper.convertToEntity(ticketDto.getEventDto())
            );
        }
        return new Ticket(
                ticketDto.getPrice(),
                ticketDto.getRow(),
                ticketDto.getSeat(),
                userMapper.convertToEntity(ticketDto.getUserDto()),
                eventMapper.convertToEntity(ticketDto.getEventDto())
        );
    }

    public TicketDto convertToDto(Ticket ticket) {
        return new TicketDto(
                ticket.getId(),
                ticket.getPrice(),
                ticket.getRow(),
                ticket.getSeat(),
                userMapper.convertToDto(ticket.getUser()),
                eventMapper.convertToDto(ticket.getEvent())
        );
    }
}
