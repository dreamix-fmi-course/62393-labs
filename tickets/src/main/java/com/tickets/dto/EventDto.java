package com.tickets.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class EventDto {
    private UUID id;
    private String name;
    private LocalDateTime date;
    private String description;

    public EventDto(UUID id, String name, LocalDateTime date, String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
    }
}
