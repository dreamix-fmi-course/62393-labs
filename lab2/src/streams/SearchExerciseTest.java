package lab2.streams;

import lab2.streams.entity.Order;
import lab2.streams.entity.OrderLine;
import lab2.streams.entity.User;
import lab2.streams.vo.OrderStatus;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static java.time.LocalDate.now;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class SearchExerciseTest {

    private SearchExercise service = new SearchExercise();

    @Test
    public void getActiveOrdersTest() {
        Order order1 = new Order(OrderStatus.ACTIVE);
        Order order2 = new Order(OrderStatus.ACTIVE);
        Order order3 = new Order(OrderStatus.DRAFT);
        User user = new User(order1, order2, order3);
        assertEquals(Arrays.asList(order1, order2), service.getActiveOrders(user));
        assertEquals(Arrays.asList(order1, order2), service.getActiveOrdersByIteration(user));
    }

    @Test
    public void getOrderByIdTest() {
        long id = 1;
        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(1));
        orders.add(new Order(2));
        orders.add(new Order(3));
        assertEquals(orders.get(0), service.getOrderById(orders, id));
        assertEquals(orders.get(0), service.getOrderByIdIteration(orders, id));
    }

    @Test
    public void getOrderByIdTest_When_id_not_found_Then_return_null() {
        long id = 0;
        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(1));
        orders.add(new Order(2));
        orders.add(new Order(3));
        assertNull(service.getOrderById(orders, id));
        assertNull(service.getOrderByIdIteration(orders, id));
    }

    @Test
    public void hasActiveOrdersTest_true() {
        Order order1 = new Order(OrderStatus.ACTIVE);
        Order order2 = new Order(OrderStatus.ACTIVE);
        Order order3 = new Order(OrderStatus.DRAFT);
        User user = new User(order1, order2, order3);
        assertTrue(service.hasActiveOrders(user));
    }

    @Test
    public void hasActiveOrdersTest_When_inactive_Then_return_false() {
        Order order1 = new Order();
        Order order2 = new Order(OrderStatus.INACTIVE);
        Order order3 = new Order(OrderStatus.DRAFT);
        User user = new User(order1, order2, order3);
        assertFalse(service.hasActiveOrders(user));
    }

    @Test
    public void canBeReturnedTest_When_no_items_Then_return_true() {
        OrderLine order1 = new OrderLine();
        OrderLine order2 = new OrderLine();
        OrderLine order3 = new OrderLine();
        Order order = new Order(order1, order2, order3);
        assertTrue(service.canBeReturned(order));
    }

    @Test
    public void canBeReturnedTest_When_item_have_SO_Then_return_false() {
        OrderLine order1 = new OrderLine();
        OrderLine order2 = new OrderLine();
        OrderLine order3 = new OrderLine(null, null, true, 0);
        Order order = new Order(order1, order2, order3);
        assertFalse(service.canBeReturned(order));
    }

    @Test
    public void getMaxPriceOrderTest() {
        Order order1 = new Order();
        Order order2 = new Order();
        Order order3 = new Order();
        order1.setTotalPrice(BigDecimal.valueOf(15));
        order2.setTotalPrice(BigDecimal.valueOf(25));
        order3.setTotalPrice(BigDecimal.valueOf(5));
        User user = new User(order1, order2, order3);
        assertEquals(BigDecimal.valueOf(25), service.getMaxPriceOrder(user).get().getTotalPrice());
    }

    @Test
    public void getMaxPriceOrderTest_When_no_orders_Then_return_nothing() {
        User user = new User();
        assertFalse(service.getMaxPriceOrder(user).isPresent());
    }
}
