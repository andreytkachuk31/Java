package com.epam.kation.facade;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Boolean.TRUE;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.kation.model.event.Event;
import com.epam.kation.model.ticket.Ticket;
import com.epam.kation.model.ticket.Ticket.Category;
import com.epam.kation.model.user.User;
import com.epam.kation.service.event.EventService;
import com.epam.kation.service.ticket.TicketService;
import com.epam.kation.service.user.UserService;

@RunWith(MockitoJUnitRunner.class)
public class DefaultBookingFacadeTest {

	private static final Long ID = 0L;
	private static final int FORTH_PAGE = 4;
	private static final int TEN_PER_PAGE_SIZE = 10;
	private static final int FORTH_PAGE_OFFSET = 30;
	private static final String TITLE = "title";
	private static final String NAME = "name";
	private static final String EMAIL = "email";
	private static final Date DATE = new Date();
	private static final Boolean REMOVE_RESULT = TRUE;

	private static final long USER_ID = 1L;
	private static final long EVENT_ID = 2L;
	private static final int PLACE = 3;
	private static final Category CATEGORY = Ticket.Category.STANDARD;

	@Mock
	private EventService eventService;

	@Mock
	private UserService userService;

	@Mock
	private TicketService ticketService;

	@Mock
	private Event event;

	@Mock
	private Event newEvent;

	@Mock
	private Event createdEvent;

	@Mock
	private Event updatedEvent;

	@Mock
	private User user;

	@Mock
	private User newUser;

	@Mock
	private User createdUser;

	@Mock
	private User updatedUser;

	@Mock
	private Ticket ticket;

	@Mock
	private Ticket newTicket;

	@Mock
	private Ticket createdTicket;

	@InjectMocks
	private BookingFacade facade = new DefaultBookingFacade(eventService, userService, ticketService);

	@Test
	public void shouldCallGetEventByIdWhenGetEventById() {
		when(eventService.get(ID)).thenReturn(of(event));

		Event actualEvent = facade.getEventById(ID);

		verify(eventService).get(ID);

		assertEquals(event, actualEvent);
	}

	@Test
	public void shouldCallCreateEventWhenCreateEvent() {
		when(eventService.create(event)).thenReturn(createdEvent);

		Event actualCreatedEvent = facade.createEvent(event);

		verify(eventService).create(event);

		assertEquals(createdEvent, actualCreatedEvent);
	}

	@Test
	public void shouldCallUpdateEventWhenUpdateEvent() {
		when(eventService.update(event)).thenReturn(updatedEvent);

		Event actualUpdatedEvent = facade.updateEvent(event);

		verify(eventService).update(event);

		assertEquals(updatedEvent, actualUpdatedEvent);
	}

	@Test
	public void shouldCallRemoveEventByIdWhenDeleteEventById() {
		when(eventService.remove(ID)).thenReturn(REMOVE_RESULT);

		boolean actualResult = facade.deleteEvent(ID);

		verify(eventService).remove(ID);

		assertEquals(REMOVE_RESULT, actualResult);
	}

	@Test
	public void shouldCallGetUserByIdWhenGetUserById() {
		when(userService.get(ID)).thenReturn(of(user));

		User actualUser = facade.getUserById(ID);

		verify(userService).get(ID);

		assertEquals(user, actualUser);
	}

	@Test
	public void shouldCallCreateUserWhenCreateUser() {
		when(userService.create(user)).thenReturn(createdUser);

		User actualCreatedUser = facade.createUser(user);

		verify(userService).create(user);

		assertEquals(createdUser, actualCreatedUser);
	}

	@Test
	public void shouldCallUpdateUserWhenUpdateUser() {
		when(userService.update(user)).thenReturn(updatedUser);

		User actualUpdatedUser = facade.updateUser(user);

		verify(userService).update(user);

		assertEquals(updatedUser, actualUpdatedUser);
	}

	@Test
	public void shouldCallRemoveUserByIdWhenDeleteUserById() {
		when(userService.remove(ID)).thenReturn(REMOVE_RESULT);

		boolean actualResult = facade.deleteUser(ID);

		verify(userService).remove(ID);

		assertEquals(REMOVE_RESULT, actualResult);
	}

	@Test
	public void shouldCallRemoveTicketByIdWhenCancelTicketById() {
		when(ticketService.remove(ID)).thenReturn(REMOVE_RESULT);

		boolean actualResult = facade.cancelTicket(ID);

		verify(ticketService).remove(ID);

		assertEquals(REMOVE_RESULT, actualResult);
	}

	@Test
	public void shouldCallGetUserByEmailWhenGetUserByEmail() {
		when(userService.getUserByEmail(EMAIL)).thenReturn(of(user));

		User actualUser = facade.getUserByEmail(EMAIL);

		verify(userService).getUserByEmail(EMAIL);

		assertEquals(user, actualUser);
	}

	@Test
	public void shouldCallCreateTicketWhenBookTicket() {
		when(ticketService.create(USER_ID, EVENT_ID, PLACE, CATEGORY)).thenReturn(createdTicket);

		Ticket actualTicket = facade.bookTicket(USER_ID, EVENT_ID, PLACE, CATEGORY);

		verify(ticketService).create(USER_ID, EVENT_ID, PLACE, CATEGORY);

		assertEquals(createdTicket, actualTicket);
	}

	@Test
	public void shouldCallGetEventByTitleWithTitleWhenGetEventsByTitle() {
		List<Event> events = newArrayList(event);
		when(eventService.getEventsByTitle(TITLE, FORTH_PAGE_OFFSET, TEN_PER_PAGE_SIZE)).thenReturn(events);

		List<Event> actualEvents = facade.getEventsByTitle(TITLE, TEN_PER_PAGE_SIZE, FORTH_PAGE);

		verify(eventService).getEventsByTitle(TITLE, FORTH_PAGE_OFFSET, TEN_PER_PAGE_SIZE);

		assertEquals(events, actualEvents);
	}

	@Test
	public void shouldCallGetEventByDayWithDateWhenGetEventsForDay() {
		List<Event> events = newArrayList(event);
		when(eventService.getEventsForDay(DATE, FORTH_PAGE_OFFSET, TEN_PER_PAGE_SIZE)).thenReturn(events);

		List<Event> actualEvents = facade.getEventsForDay(DATE, TEN_PER_PAGE_SIZE, FORTH_PAGE);

		verify(eventService).getEventsForDay(DATE, FORTH_PAGE_OFFSET, TEN_PER_PAGE_SIZE);

		assertEquals(events, actualEvents);
	}

	@Test
	public void shouldCallGetUserByNameWithNameWhenGetUserByName() {
		ArrayList<User> users = newArrayList(user);
		when(userService.getUsersByName(NAME, FORTH_PAGE_OFFSET, TEN_PER_PAGE_SIZE)).thenReturn(users);

		List<User> actualUsers = facade.getUsersByName(NAME, TEN_PER_PAGE_SIZE, FORTH_PAGE);

		verify(userService).getUsersByName(NAME, FORTH_PAGE_OFFSET, TEN_PER_PAGE_SIZE);

		assertEquals(users, actualUsers);
	}

	@Test
	public void shouldCallGetTicketByUserithUserWhenGetBookedTicketsWithUser() {
		List<Ticket> tickets = newArrayList(ticket);
		when(ticketService.getTicketsByUser(user, FORTH_PAGE_OFFSET, TEN_PER_PAGE_SIZE)).thenReturn(tickets);

		List<Ticket> actualTickets = facade.getBookedTickets(user, TEN_PER_PAGE_SIZE, FORTH_PAGE);

		verify(ticketService).getTicketsByUser(user, FORTH_PAGE_OFFSET, TEN_PER_PAGE_SIZE);

		assertEquals(tickets, actualTickets);
	}

	@Test
	public void shouldCallGetTicketByEventWithEventWhenGetBookedTicketsWithEvent() {
		ArrayList<Ticket> tickets = newArrayList(ticket);
		when(ticketService.getTicketsByEvent(event, FORTH_PAGE_OFFSET, TEN_PER_PAGE_SIZE)).thenReturn(tickets);

		List<Ticket> actualTickets = facade.getBookedTickets(event, TEN_PER_PAGE_SIZE, FORTH_PAGE);

		verify(ticketService).getTicketsByEvent(event, FORTH_PAGE_OFFSET, TEN_PER_PAGE_SIZE);

		assertEquals(tickets, actualTickets);
	}
}
