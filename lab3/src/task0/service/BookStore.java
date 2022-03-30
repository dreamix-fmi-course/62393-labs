package lab3.task0.service;

import lab3.task0.model.*;
import lab3.task0.repository.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.function.Predicate;

public class BookStore implements Store {
    private CRUD dbCrud = new BookStoreDB();

    public boolean add(Book book) {
        Book byKey = dbCrud.getByKey(book.getISBN());
        if (byKey != null && byKey.equals(book)) {
            return false;
        }
        dbCrud.add(book);
        return true;
    }

    public void remove(Book book) {
        this.dbCrud.remove(book.getISBN());
    }

    public List<Book> getAllBooksByAuthor(String author) {
        return this.dbCrud.getAll().stream()
            .filter(b -> b.getAuthor().equals(author))
            .collect(Collectors.toList());
    }

    public List<Book> getAllBooksPublishedAfter(LocalDate from) {
        return this.dbCrud.getAll().stream()
            .filter(b -> b.getPublishedYear().isAfter(from))
            .collect(Collectors.toList());
    }

    public List<Book> getAllBooksBetween(LocalDate from, LocalDate to) {
        return this.dbCrud.getAll().stream()
            .filter(b -> b.getPublishedYear().isAfter(from) && b.getPublishedYear().isBefore(to))
            .collect(Collectors.toList());
    }

    public void clear() {
        this.dbCrud.clear();
    }

    public Map<String, List<Book>> getAllBooksGroupByAuthor() {
        return this.dbCrud.getAll().stream()
            .collect(Collectors.groupingBy(b -> b.getAuthor()));
    }

    public Map<String, List<Book>> getAllBooksGroupByPublisher() {
        return this.dbCrud.getAll().stream()
            .collect(Collectors.groupingBy(b -> b.getPublisher()));
    }

    public List<Book> getAllBooksFilterBy(Predicate<Book> bookPredicate) {
        return this.dbCrud.getAll().stream()
            .filter(bookPredicate)
            .collect(Collectors.toList());
    }
}
