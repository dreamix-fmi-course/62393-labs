package lab4.task0.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lab4.task0.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookStoreDB implements CRUD {   
    private Map<String, Book> db = new ConcurrentHashMap<String, Book>();

    @Override
    public void add(Book book) {
        this.db.put(book.getISBN(), book);
    }

    @Override
    public Book update(Book book) {
        Book bookToUpdate = this.getByKey(book.getISBN());

        if(!book.equals(bookToUpdate)) {
            db.put(book.getISBN(), book);
        }

        return book;
    }

    @Override
    public void remove(String ISBN) {
        this.db.remove(ISBN);
    }

    public void clear() {
        this.db.clear();
    }

    @Override
    public Book getByKey(String ISBN) {
        return this.db.get(ISBN);
    }

    @Override
    public List<Book> getAll() {
        // return db.values.stream().collect(Collectors.toList());
        return new ArrayList<Book>(this.db.values());
    }
}
