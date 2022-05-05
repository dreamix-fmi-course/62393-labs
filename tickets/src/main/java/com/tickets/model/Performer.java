package com.tickets.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@ToString
@Table(name="performers")
public class Performer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private UUID id;

    @Column(name="name", nullable=false, length=16)
    private String name;

    @Column(name = "stage_name", nullable=false)
    private String stageName;

    @Column(name = "stage_age", nullable=false)
    private int stageAge;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "performers_events_join",
            joinColumns = {
                    @JoinColumn(name = "performer_id", referencedColumnName = "id", nullable = false, updatable = false),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false, updatable = false),
            })
    private Set<Event> events;

    public Performer() {}

    public Performer(String name, String stageName, int stageAge) {
        this(UUID.randomUUID(), name, stageName, stageAge);
    }

    public Performer(UUID id, String name, String stageName, int stageAge) {
        this.id = id;
        this.name = name;
        this.stageName = stageName;
        this.stageAge = stageAge;
    }

    @PrePersist
    private void onCreate() {
        this.setId(UUID.randomUUID());
    }
}
