package lab5.tickets;

import lab5.tickets.config.AppConfig;
import lab5.tickets.logger.Logger;
import lab5.tickets.service.EventService;
import lab5.tickets.service.TicketService;
import lab5.tickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketsApplication implements CommandLineRunner {

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

    @Override
    public void run(String... args) throws Exception {
        logger.info(appConfig.getEvent());
        logger.info(appConfig.getLogger());
    }
}
