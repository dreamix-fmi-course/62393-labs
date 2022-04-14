package lab6.tickets.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
public class Ticket {
    private UUID id;
    private BigDecimal price;
    private int row;
    private int seat;
    private User user;
    private Event event;

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

    public void setPrice(BigDecimal price) {
        this.price = price;
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