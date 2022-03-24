package lab4.task1;

import lab4.task1.model.User;
import lab4.task1.service.EventService;
import lab4.task1.service.TicketService;
import lab4.task1.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class Task1ApplicationTests {

	@Autowired
	private EventService eventService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private UserService userService;

	@Test
	void testEventService() {
		User u1 = new User("user1", "user1@mail.bg");
		User u2 = new User("user2", "user2@mail.bg");
		User u3 = new User("user3", "user3@mail.bg");

		this.userService.createUser(u1);
		this.userService.createUser(u2);
		this.userService.createUser(u3);

		assert this.userService.findById(u1.getId()) != null;
		assert this.userService.findById(u2.getId()) != null;
		assert this.userService.findById(u3.getId()) != null;
		assert this.userService.findById(UUID.randomUUID()) == null;
	}
}
