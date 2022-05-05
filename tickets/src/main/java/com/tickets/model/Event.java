package com.tickets.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
@EqualsAndHashCode
@ToString
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private UUID id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;

    @Column(name = "date", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime date;

    @Column(name = "description", columnDefinition = "VARCHAR(255)")
    private String description;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private Set<Ticket> tickets;

    @ManyToMany(mappedBy = "events", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Performer> performers;

    public Event() {}

    public Event(String name, LocalDateTime date, String description) {
        this(UUID.randomUUID(), name, date, description);
    }

    public Event(UUID id, String name, LocalDateTime date, String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
    }

    @PrePersist
    private void onCreate() {
        this.setId(UUID.randomUUID());
    }
}