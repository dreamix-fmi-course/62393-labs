package lab2.streams.entity;

import lab2.streams.vo.OrderLineStatus;

/**
 * Create OrderLine which holds information for:
 * Item, enum for status, boolean specialOffer and count
 * Implement setters/getters/constructor
 */
public class OrderLine {
    private Item item;
    private OrderLineStatus status;
    private boolean specialOffer;
    private int count;

    public OrderLine() {
        this.item = null;
        this.status = null;
        this.specialOffer = false;
        this.count = 0;
    }

    public OrderLine(Item item, OrderLineStatus status, boolean specialOffer, int count) {
        this.item = item;
        this.status = status;
        this.specialOffer = specialOffer;
        this.count = count;
    }

    public Item getItem() { return this.item; }
    public OrderLineStatus getStatus() { return this.status; }
    public boolean getSpecialOffer() { return this.specialOffer; }
    public int getCount() { return this.count; }

    public void setItem(Item newItem) { this.item = newItem; }
    public void setStatus(OrderLineStatus newStatus) { this.status = newStatus; }
    public void setSpecialOffer(boolean newSpecialOffer) { this.specialOffer = newSpecialOffer; }
    public void setCount(int newCount) { this.count = newCount; }
}
