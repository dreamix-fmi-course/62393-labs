package com.tickets.dto;

import com.tickets.model.Event;
import com.tickets.model.User;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
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

    @Override
    public String toString() {
        return "TicketDto{" +
                "id=" + id +
                ", price=" + price +
                ", row=" + row +
                ", seat=" + seat +
                ", userDto=" + userDto +
                ", eventDto=" + eventDto +
                '}';
    }
}
