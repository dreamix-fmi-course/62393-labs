package com.tickets.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@ToString
public class TicketDto {
    private UUID id;
    private BigDecimal price;
    private int row;
    private int seat;
    private UserDto userDto;
    private EventDto eventDto;

    public TicketDto(UUID id, BigDecimal price, int row, int seat, UserDto userDto, EventDto eventDto) {
        this.id = id;
        this.price = price;
        this.row = row;
        this.seat = seat;
        this.userDto = userDto;
        this.eventDto = eventDto;
    }
}
