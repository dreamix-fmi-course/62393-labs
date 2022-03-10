package lab2.streams.entity;

import java.util.*;

/**
 * Create a class User with list of orders
 */
public class User {
    private List<Order> orders;

    public User() {
        this.orders = new ArrayList<Order>();
    }

    public User(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() { return this.orders; }
    
    public void setOrders(List<Order> newOrders) { this.orders = newOrders; }

    public void addOrder(Order order) { this.orders.add(order); }
}
