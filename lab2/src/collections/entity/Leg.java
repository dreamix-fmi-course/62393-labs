package collections.entity;

import java.util.*;

public class Leg {
    private String fromAirport;
    private String toAirport;
    private Date date;

    public Leg() {
        this.fromAirport = null;
        this.toAirport = null;
        this.date = null;
    }

    public Leg(String fromAirport, String toAirport, Date date) {
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.date = date;
    }

    public String getFromAirport() { return this.fromAirport; }
    public String getToAirport() { return this.toAirport; }
    public Date getDate() { return this.date; }

    public void setFromAirport(String newFromAirport) { this.fromAirport = newFromAirport; }
    public void setToAirport(String newToAirport) { this.toAirport = newToAirport; }
    public void setDate(Date newDate) { this.date = newDate; }
}
