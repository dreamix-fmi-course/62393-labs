package lab2.streams.entity;

import java.math.BigDecimal;

/**
 * Implement Item with description:String and price:BigDecimal
 */

public class Item {
    private String description;
    private BigDecimal price;

    public Item() {
        this.description = null;
        this.price = null;
    }

    public Item(String description, BigDecimal price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() { return this.description; }
    public BigDecimal getprice() { return this.price; }

    public void setDescription(String newDescription) { this.description = newDescription; }
    public void setprice(BigDecimal newPrice) { this.price = newPrice; }
}
