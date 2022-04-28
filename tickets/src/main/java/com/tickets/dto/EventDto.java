package com.tickets.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
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

    @Override
    public String toString() {
        return "EventDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
