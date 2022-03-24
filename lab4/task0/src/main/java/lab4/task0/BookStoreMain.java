package lab4.task0;

import lab4.task0.model.*;
import lab4.task0.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class BookStoreMain implements CommandLineRunner {
    @Autowired
    private BookStore bs;

    public static void main(String... args) {
        SpringApplication.run(BookStoreMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Book b1 = new Book("Book1", "Me", new BigDecimal(0), "He", LocalDate.now());
        Book b2 = new Book("Book2", "You", new BigDecimal(5.99), "He", LocalDate.now().minusDays(2));
        Book b3 = new Book("Book3", "Me", new BigDecimal(5.99), "She", LocalDate.now().minusDays(3));
        Book b4 = new Book("Book4", "You", new BigDecimal(15.99), "She", LocalDate.now().minusDays(4));

        bs.add(b1);
        bs.add(b2);
        bs.add(b3);
        bs.add(b4);

        System.out.println("=============== Add existing book ==============");
        System.out.println(bs.add(b3));
        System.out.println();

        System.out.println("=============== Books by author 'Me' ==============");
        System.out.println(bs.getAllBooksByAuthor("Me"));
        System.out.println();

        System.out.println(String.format("=============== Books published after %s ==============", LocalDate.now().minusDays(3)));
        System.out.println(bs.getAllBooksPublishedAfter(LocalDate.now().minusDays(3)));
        System.out.println();

        System.out.println(String.format("=============== Books published after %s and before %s ==============", LocalDate.now().minusDays(3), LocalDate.now()));
        System.out.println(bs.getAllBooksBetween(LocalDate.now().minusDays(3), LocalDate.now()));
        System.out.println();

        System.out.println("=============== Books grouped by author ==============");
        System.out.println(bs.getAllBooksGroupByAuthor());
        System.out.println();

        System.out.println("=============== Books grouped by publisher ==============");
        System.out.println(bs.getAllBooksGroupByPublisher());
        System.out.println();

        System.out.println("=============== Books filtered by price > 4.99 ==============");
        System.out.println(bs.getAllBooksFilterBy(b -> b.getPrice().compareTo(new BigDecimal(4.99)) > 0));
        System.out.println();

        System.out.println("=============== Remove book ==============");
        bs.remove(bs.getAllBooksFilterBy(b -> b.getPrice().compareTo(new BigDecimal(4.99)) > 0).get(0));
        System.out.println(bs.getAllBooksFilterBy(b -> b.getPrice().compareTo(new BigDecimal(4.99)) > 0));
        System.out.println();

        System.out.println("=============== Clear db ==============");
        System.out.println(bs.getAllBooksFilterBy(b -> true));
        bs.clear();
        System.out.println(bs.getAllBooksFilterBy(b -> true));
    }
}
