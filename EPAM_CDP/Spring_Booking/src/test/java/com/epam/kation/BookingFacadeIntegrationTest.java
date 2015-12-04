package com.epam.kation;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.kation.facade.BookingFacade;
import com.epam.kation.model.event.DefaultEvent;
import com.epam.kation.model.event.Event;
import com.epam.kation.model.ticket.Ticket;
import com.epam.kation.model.user.DefaultUser;
import com.epam.kation.model.user.User;

public class BookingFacadeIntegrationTest {

	private static final String EVENTICKETSBOOKING_SPRING_XML = "eventicketsbooking-spring.xml";

	@Test
	public void shouldStartSpringContext() {
		ApplicationContext context = new ClassPathXmlApplicationContext(EVENTICKETSBOOKING_SPRING_XML);

		BookingFacade facade = context.getBean(BookingFacade.class);

		User user = new DefaultUser();
		user.setName("SomeName");
		user.setEmail("someemail@email.com");

		user = facade.createUser(user);

		Event event = new DefaultEvent();
		event.setTitle("SomeTitle");
		event.setDate(new Date());

		event = facade.createEvent(event);

		Ticket ticket = facade.bookTicket(user.getId(), event.getId(), 1, Ticket.Category.STANDARD);

		assertTrue(facade.cancelTicket(ticket.getId()));
	}
}
