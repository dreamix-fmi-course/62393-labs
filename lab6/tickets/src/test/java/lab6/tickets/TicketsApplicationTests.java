package lab6.tickets;

import lab6.tickets.model.Event;
import lab6.tickets.model.Ticket;
import lab6.tickets.model.User;
import lab6.tickets.service.EventService;
import lab6.tickets.service.TicketService;
import lab6.tickets.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
class TicketsApplicationTests {

	@Autowired
	private EventService eventService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private UserService userService;

	@Test
	void testUserServiceFindById() {
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

	@Test
	void testUserServiceDeleteUser() {
		User u1 = new User("user1", "user1@mail.bg");
		User u2 = new User("user2", "user2@mail.bg");
		User u3 = new User("user3", "user3@mail.bg");

		this.userService.createUser(u1);
		this.userService.createUser(u2);
		this.userService.createUser(u3);

		this.userService.deleteUser(u1.getId());
		this.userService.deleteUser(UUID.randomUUID());

		assert this.userService.findById(u1.getId()) == null;
	}

	@Test
	void testUserServiceUpdateUser() {
		User u1 = new User("user1", "user1@mail.bg");
		User u2 = new User("user2", "user2@mail.bg");
		User u3 = new User("user3", "user3@mail.bg");

		this.userService.createUser(u1);
		this.userService.createUser(u2);
		this.userService.createUser(u3);

		u2.setUserName("newuser2");
		this.userService.updateUserInformation(u2);

		assert this.userService.findById(u2.getId()).getUserName().equals(u2.getUserName());
	}

	@Test
	void testUserServiceGetAllVisitedEvents() throws Exception {
		User u = new User("user", "user@mail.bg");
		Event e1 = new Event("event1", LocalDateTime.now().plusDays(1), "first event");
		Event e2 = new Event("event2", LocalDateTime.now().plusWeeks(1), "second event");
		Event e3 = new Event("event2", LocalDateTime.now(), "third event");

		Thread.sleep(1000);

		Ticket t1 = new Ticket(new BigDecimal(2.50), 2, 4, u, e1);
		Ticket t2 = new Ticket(new BigDecimal(2.50), 2, 4, u, e2);
		Ticket t3 = new Ticket(new BigDecimal(2.50), 2, 5, u, e2);
		Ticket t4 = new Ticket(new BigDecimal(2.50), 2, 4, u, e3);

		this.ticketService.createTicket(t1);
		this.ticketService.createTicket(t2);
		this.ticketService.createTicket(t3);
		this.ticketService.createTicket(t4);

		assert this.userService.getAllVisitedEvent().size() == 1;
		assert this.userService.getAllVisitedEventsInPastMonth().size() == 1;
	}

	@Test
	void testEventServiceFindById() {
		this.eventService.getAllEvents().forEach(e -> this.eventService.removeEvent(e.getEventId()));

		Event e1 = new Event("event1", LocalDateTime.now().plusDays(1), "first event");
		Event e2 = new Event("event2", LocalDateTime.now().plusWeeks(1), "second event");
		Event e3 = new Event("event2", LocalDateTime.now(), "third event");

		this.eventService.createEvent(e1);
		this.eventService.createEvent(e2);
		this.eventService.createEvent(e3);

		assert this.eventService.findById(e1.getEventId()) != null;
		assert this.eventService.findById(e2.getEventId()) != null;
		assert this.eventService.findById(e3.getEventId()) != null;
		assert this.eventService.findById(UUID.randomUUID()) == null;
	}

	@Test
	void testEventServiceRemoveEvent() {
		this.eventService.getAllEvents().forEach(e -> this.eventService.removeEvent(e.getEventId()));

		Event e1 = new Event("event1", LocalDateTime.now().plusDays(1), "first event");
		Event e2 = new Event("event2", LocalDateTime.now().plusWeeks(1), "second event");
		Event e3 = new Event("event2", LocalDateTime.now(), "third event");

		this.eventService.createEvent(e1);
		this.eventService.createEvent(e2);
		this.eventService.createEvent(e3);

		assert this.eventService.findById(e1.getEventId()) != null;

		this.eventService.removeEvent(e1.getEventId());

		assert this.eventService.findById(e1.getEventId()) == null;
	}

	@Test
	void testEventServiceUpdateEvent() {
		Event e1 = new Event("event1", LocalDateTime.now().plusDays(1), "first event");
		Event e2 = new Event("event2", LocalDateTime.now().plusWeeks(1), "second event");
		Event e3 = new Event("event2", LocalDateTime.now(), "third event");

		this.eventService.createEvent(e1);
		this.eventService.createEvent(e2);
		this.eventService.createEvent(e3);

		assert this.eventService.findById(e1.getEventId()).getName().equals("event1");

		e1.setName("newevent1");
		this.eventService.updateEvent(e1);

		assert this.eventService.findById(e1.getEventId()).getName().equals("newevent1");
	}

	@Test
	void testTicketServiceFindById() throws Exception {
		User u = new User("user", "user@mail.bg");
		Event e = new Event("event1", LocalDateTime.now().plusDays(1), "first event");
		Ticket t = new Ticket(new BigDecimal(2.50), 1, 2, u, e);

		this.ticketService.createTicket(t);

		assert this.ticketService.findById(t.getTicketId()) != null;
		assert this.ticketService.findById(UUID.randomUUID()) == null;
	}

	@Test
	void testTicketServiceRemoveTicket() throws Exception {
		User u = new User("user", "user@mail.bg");
		Event e = new Event("event1", LocalDateTime.now().plusDays(1), "first event");
		Ticket t = new Ticket(new BigDecimal(2.50), 1, 2, u, e);

		this.ticketService.createTicket(t);

		assert this.ticketService.findById(t.getTicketId()) != null;

		this.ticketService.removeTicket(t.getTicketId());

		assert this.ticketService.findById(t.getTicketId()) == null;
	}

	@Test
	void testTicketServiceUpdateTicket() throws Exception {
		User u = new User("user", "user@mail.bg");
		Event e = new Event("event1", LocalDateTime.now().plusDays(1), "first event");
		Ticket t = new Ticket(new BigDecimal(2.50), 1, 2, u, e);

		this.ticketService.createTicket(t);

		assert this.ticketService.findById(t.getTicketId()).getPrice().equals(new BigDecimal(2.50));

		t.setPrice(new BigDecimal(3.00));
		this.ticketService.updateTicket(t);

		assert this.ticketService.findById(t.getTicketId()).getPrice().equals(new BigDecimal(3.00));
	}

	@Test
	void testTicketServiceFindAllTicketsByUser() throws Exception {
		User u1 = new User("user1", "user1@mail.bg");
		User u2 = new User("user2", "user2@mail.bg");
		Event e = new Event("event1", LocalDateTime.now().plusDays(1), "first event");
		Ticket t1 = new Ticket(new BigDecimal(2.50), 1, 2, u1, e);
		Ticket t2 = new Ticket(new BigDecimal(2.50), 1, 3, u2, e);
		Ticket t3 = new Ticket(new BigDecimal(2.50), 1, 4, u1, e);

		this.ticketService.createTicket(t1);
		this.ticketService.createTicket(t2);
		this.ticketService.createTicket(t3);

		assert this.ticketService.findAllTicketsByUser(u1).size() == 2;
		assert this.ticketService.findAllTicketsByUser(u2).size() == 1;
	}

	@Test
	void testTicketServiceFindAllTicketsByEvent() throws Exception {
		User u = new User("user1", "user1@mail.bg");
		Event e1 = new Event("event1", LocalDateTime.now().plusDays(1), "first event");
		Event e2 = new Event("event2", LocalDateTime.now().plusDays(1), "second event");
		Ticket t1 = new Ticket(new BigDecimal(2.50), 1, 2, u, e1);
		Ticket t2 = new Ticket(new BigDecimal(2.50), 1, 3, u, e2);
		Ticket t3 = new Ticket(new BigDecimal(2.50), 1, 4, u, e1);

		this.ticketService.createTicket(t1);
		this.ticketService.createTicket(t2);
		this.ticketService.createTicket(t3);

		assert this.ticketService.findAllTicketsByEvent(e1).size() == 2;
		assert this.ticketService.findAllTicketsByEvent(e2).size() == 1;
	}

	@Test
	void testTicketServiceFindAllEventsBetweenDates() throws Exception {
		User u = new User("user1", "user1@mail.bg");
		Event e1 = new Event("event1", LocalDateTime.now().plusDays(1), "first event");
		Event e2 = new Event("event2", LocalDateTime.now().plusDays(15), "second event");
		Event e3 = new Event("event3", LocalDateTime.now().plusMonths(1), "third event");
		Ticket t1 = new Ticket(new BigDecimal(2.50), 1, 2, u, e1);
		Ticket t2 = new Ticket(new BigDecimal(2.50), 1, 3, u, e2);
		Ticket t3 = new Ticket(new BigDecimal(2.50), 1, 4, u, e3);

		this.ticketService.createTicket(t1);
		this.ticketService.createTicket(t2);
		this.ticketService.createTicket(t3);

		assert this.ticketService.findAllEventsBetweenDates(LocalDate.now(), LocalDate.now().plusDays(17)).size() == 2;
	}
}
