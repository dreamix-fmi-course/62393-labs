package lab2.streams;

import lab2.streams.entity.Order;
import lab2.streams.entity.OrderLine;
import lab2.streams.entity.User;
import lab2.streams.vo.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

import static java.util.stream.Collectors.toList;

public class SearchExercise {

    /**
     * extract all active orders
     * @param user
     * @return List<Order>
     */
    public List<Order> getActiveOrders(User user) {
        return user.getOrders()
            .stream()
            .filter(order -> order.getStatus() == OrderStatus.ACTIVE)
            .collect(toList());
    }

    public List<Order> getActiveOrdersByIteration(User user) {
        List<Order> activeOrders = new ArrayList<Order>();
        for (Order order : user.getOrders()) {
            if (order.getStatus() == OrderStatus.ACTIVE) activeOrders.add(order);
        }
        return activeOrders;
    }

    /**
     * Return order by a specific id
     * @param orders
     * @param orderId
     * @return Order
     */
    public Order getOrderById(List<Order> orders, long orderId) {
        return orders.stream()
            .filter(o -> o.getId() == orderId)
            .findFirst().orElse(null);
    }

    public Order getOrderByIdIteration(List<Order> orders, long orderId) {
        for (Order o : orders) {
            if (o.getId() == orderId) return o;
        }
        return null;
    }

    /**
     * Return orders that have specific description for item
     * @param user
     * @param description
     * @return List<Order>
     */
    public List<Order> getOrdersThatHaveItemDescription(User user, String description) {
        return user.getOrders().stream()
            .filter(o -> o.getOrderLines().stream()
                    .anyMatch(l -> l.getItem().getDescription().equals(description)))
            .collect(toList());
    }

    /**
     * @return true if customer has at least one order with status ACTIVE
     */
    public boolean hasActiveOrders(User user) {
        return user.getOrders().stream()
            .anyMatch(o -> o.getStatus() == OrderStatus.ACTIVE);
    }

    /**
     * Return true if inside the Order we don't have OrderLine with special offer
     */
    public boolean canBeReturned(Order order) {
        return !order.getOrderLines().stream()
            .anyMatch(l -> l.getSpecialOffer());
    }

    /**
     * Return the order with maximum total price
     * @param user
     * @return
     */
    public Optional<Order> getMaxPriceOrder(User user) {
        return user.getOrders().stream()
            .max((o1, o2) -> o1.getTotalPrice().compareTo(o2.getTotalPrice()));
    }
}
