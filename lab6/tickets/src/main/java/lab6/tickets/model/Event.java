package lab6.tickets.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
public class Event {
    private UUID id;
    private String name;
    private LocalDateTime date;
    private String description;

    public Event(String name, LocalDateTime date, String description) {
        this(UUID.randomUUID(), name, date, description);
    }

    public Event(UUID id, String name, LocalDateTime date, String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, description);
    }
}