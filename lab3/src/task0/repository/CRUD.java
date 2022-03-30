package lab3.task0.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import lab3.task0.model.Book;

public interface CRUD {
    void add(Book book);

    Book update(Book book);

    void remove(String ISBN);

    void clear();
    
    Book getByKey(String ISBN);

    List<Book> getAll();
}
