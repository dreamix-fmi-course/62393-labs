package com.tickets.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import org.hibernate.annotations.CascadeType;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", price=" + price +
                ", row=" + row +
                ", seat=" + seat +
                ", user=" + user +
                ", event=" + event +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, row, seat, user, event);
    }
}