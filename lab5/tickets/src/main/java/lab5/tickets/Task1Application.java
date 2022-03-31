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
public class Task1Application implements CommandLineRunner {

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
		SpringApplication.run(Task1Application.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        logger.info(appConfig.getEventConfig());
        logger.info(appConfig.maximum_seat);
//        String[] names = appConfig.getEventConfig().getNames();
//        String[] descriptions = appConfig.getEventConfig().getDescriptions();
//        for (int i=0; i < names.length; i++) {
//            this.eventService.createEvent(new Event(names[i], LocalDateTime.now(), descriptions[i]));
//        }
    }
}
