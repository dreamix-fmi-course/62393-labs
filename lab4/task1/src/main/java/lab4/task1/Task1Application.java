package lab4.task1;

import lab4.task1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task1Application implements CommandLineRunner {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(Task1Application.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

    }
}
