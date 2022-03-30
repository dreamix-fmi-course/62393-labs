package lab4.task0.repository;

import lab4.task0.model.Book;
import java.util.List;

public interface CRUD {
    void add(Book book);

    Book update(Book book);

    void remove(String ISBN);

    void clear();
    
    Book getByKey(String ISBN);

    List<Book> getAll();
}
