package lab4.task1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
public class Event {
    private UUID eventId;
    private String name;
    private LocalDateTime date;
    private String description;

    public Event(String name, LocalDateTime date, String description) {
        this.eventId = UUID.randomUUID();
        this.name = name;
        this.date = date;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(eventId, event.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, name, date, description);
    }
}