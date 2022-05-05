package com.tickets.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@ToString
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private UUID id;

    @Column(name = "price", columnDefinition = "DECIMAL(19, 2)", nullable = false)
    private BigDecimal price;

    @Column(name = "row", nullable = false)
    private int row;

    @Column(name = "seat", nullable = false)
    private int seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    public Ticket() {}

    public Ticket(BigDecimal price, int row, int seat, User user, Event event) {
        this(UUID.randomUUID(), price, row, seat, user, event);
    }

    public Ticket(UUID id, BigDecimal price, int row, int seat, User user, Event event) {
        this.id = id;
        this.price = price;
        this.row = row;
        this.seat = seat;
        this.user = user;
        this.event = event;
    }

    @PrePersist
    private void onCreate() {
        this.setId(UUID.randomUUID());
    }
}