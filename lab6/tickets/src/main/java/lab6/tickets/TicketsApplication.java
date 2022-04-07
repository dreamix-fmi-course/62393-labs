package lab6.tickets;

import lab6.tickets.config.AppConfig;
import lab6.tickets.logger.Logger;
import lab6.tickets.service.EventService;
import lab6.tickets.service.TicketService;
import lab6.tickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketsApplication {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;
    @Autowired
    private Logger logger;
    @Autowired
    private AppConfig appConfig;

	public static void main(String[] args) {
		SpringApplication.run(TicketsApplication.class, args);
	}
}
