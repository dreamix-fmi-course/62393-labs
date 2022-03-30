package lab2.streams.entity;

import java.math.BigDecimal;
import java.util.*;
import lab2.streams.vo.*;

/** Create class Order that implements
 * id, status:OrderStatus, List<OrderLine> orderLines, creationDate, totalPrice, paymentMethod,
 * deliveryDueDate, user
 *
 * Implements constructors: default, by status, by id, by array of lines (use ...)
 * getters, setters, toString, isActive
 */
public class Order {
    private Long id;
    private OrderStatus status;
    private List<OrderLine> orderLines;
    private Date creationDate;
    private BigDecimal totalPrice;
    private PaymentMethod paymentMethod;
    private Date deliveryDueDate;
    private User user;

    public Order() {
        this.id = null;
        this.status = OrderStatus.INACTIVE;
        this.orderLines = new ArrayList<OrderLine>();
        this.creationDate = Calendar.getInstance().getTime();
        this.totalPrice = BigDecimal.ZERO;
        this.paymentMethod = null;
        this.deliveryDueDate = null;
        this.user = null;
    }

    public Order(OrderStatus status) {
        this();
        this.status = status;
    }

    public Order(long id) {
        this();
        this.id = id;
    }

    public Order(OrderLine... orderLines) {
        this();
        this.orderLines = Arrays.asList(orderLines);
    }

    public Long getId() { return this.id; }
    public OrderStatus getStatus() { return this.status; }
    public List<OrderLine> getOrderLines() { return this.orderLines; }
    public Date getCreationDate() { return this.creationDate; }
    public BigDecimal getTotalPrice() { return this.totalPrice; }
    public PaymentMethod getPaymentMethod() { return this.paymentMethod; }
    public Date getDeliveryDueDate() { return this.deliveryDueDate; }
    public User getUser() { return this.user; }

    public void setId(long newId) { this.id = newId; }
    public void setStatus(OrderStatus newStatus) { this.status = newStatus; }
    public void setOrderLines(List<OrderLine> newOrderLines) { this.orderLines = newOrderLines; }
    public void setCreationDate(Date newCreationDate) { this.creationDate = newCreationDate; }
    public void setTotalPrice(BigDecimal newTotalPrice) { this.totalPrice = newTotalPrice; }
    public void setPaymentMethod(PaymentMethod newPaymentMethod) { this.paymentMethod = newPaymentMethod; }
    public void setDeliveryDueDate(Date newDeliveryDueDate) { this.deliveryDueDate = newDeliveryDueDate; }
    public void setUser(User newUser) { this.user = newUser; }

    public boolean isActive() {
        return this.status == OrderStatus.ACTIVE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); 

        sb.append(String.format("ID: %d", this.id));
        sb.append(String.format("Status: %s", this.status));
        sb.append("Order lines:");
        this.orderLines.forEach(line -> sb.append(String.format("%5s", line)));
        sb.append(String.format("Creation date: %s", this.creationDate));
        sb.append(String.format("Total price: %.2f", this.totalPrice));
        sb.append(String.format("Payment method: %s", this.paymentMethod));
        sb.append(String.format("Delivery due date: %s", this.deliveryDueDate));
        sb.append(String.format("User: %s", this.user));

        return sb.toString();
    }
}
