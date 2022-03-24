package lab4.task0.model;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.Objects;
import java.text.NumberFormat;

public class Book {
    private String ISBN;
    private String title;
    private String author;
    private BigDecimal price;
    private String publisher;
    private LocalDate publishedYear;

    public Book(String title, String author, BigDecimal price, String publisher, LocalDate publishedYear) {
        this.ISBN = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.price = price;
        this.publisher = publisher;
        this.publishedYear = publishedYear;
    }

    public String getISBN() { return this.ISBN; };
    public String getTitle() { return this.title; };
    public String getAuthor() { return this.author; };
    public BigDecimal getPrice() { return this.price; };
    public String getPublisher() { return this.publisher; };
    public LocalDate getPublishedYear() { return this.publishedYear; };

    // public void setISBN(String newISBN) { this.ISBN = newISBN; };
    public void setTitle(String newTitle) { this.title = newTitle; };
    public void setAuthor(String newAuthor) { this.author = newAuthor; };
    public void setPrice(BigDecimal newPrice) { this.price = newPrice; };
    public void setPublisher(String newPublisher) { this.publisher = newPublisher; };
    public void setPublishedYear(LocalDate newPublishedYear) { this.publishedYear = newPublishedYear; };
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Book other = (Book)o;
        return Objects.equals(other.ISBN, this.ISBN)
            && Objects.equals(other.title, this.title)
            && Objects.equals(other.author, this.author)
            && Objects.equals(other.price, this.price)
            && Objects.equals(other.publisher, this.publisher)
            && Objects.equals(other.publishedYear, this.publishedYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN, title, author, price, publisher, publishedYear);
    }

    @Override
    public String toString() {
        return "Book {" +
            "ISBN=\"" + this.ISBN + "\", " +
            "title=\"" + this.title + "\", " +
            "author=\"" + this.author + "\", " +
            "price=\"" + NumberFormat.getCurrencyInstance().format(this.price) + "\", " +
            "publisher=\"" + this.publisher + "\", " +
            "publishedYear=\"" + this.publishedYear + "\"" +
        "}";
    }
}
