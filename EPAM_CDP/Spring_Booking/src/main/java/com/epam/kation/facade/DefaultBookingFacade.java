package com.epam.kation.facade;

import java.util.Date;
import java.util.List;

import com.epam.kation.model.event.Event;
import com.epam.kation.model.ticket.Ticket;
import com.epam.kation.model.ticket.Ticket.Category;
import com.epam.kation.model.user.User;
import com.epam.kation.service.event.EventService;
import com.epam.kation.service.ticket.TicketService;
import com.epam.kation.service.user.UserService;

public class DefaultBookingFacade implements BookingFacade {

	private EventService eventService;
	private UserService userService;
	private TicketService ticketService;

	public DefaultBookingFacade(EventService eventService, UserService userService, TicketService ticketService) {
		this.eventService = eventService;
		this.userService = userService;
		this.ticketService = ticketService;
	}

	@Override
	public Event getEventById(long eventId) {
		return eventService.get(eventId).get();
	}

	@Override
	public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
		return eventService.getEventsByTitle(title, countOffset(pageSize, pageNum), pageSize);
	}

	@Override
	public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
		return eventService.getEventsForDay(day, countOffset(pageSize, pageNum), pageSize);
	}

	@Override
	public Event createEvent(Event event) {
		return eventService.create(event);
	}

	@Override
	public Event updateEvent(Event event) {
		return eventService.update(event);
	}

	@Override
	public boolean deleteEvent(long eventId) {
		return eventService.remove(eventId);
	}

	@Override
	public User getUserById(long userId) {
		return userService.get(userId).get();
	}

	@Override
	public User getUserByEmail(String email) {
		return userService.getUserByEmail(email).get();
	}

	@Override
	public List<User> getUsersByName(String name, int pageSize, int pageNum) {
		return userService.getUsersByName(name, countOffset(pageSize, pageNum), pageSize);
	}

	@Override
	public User createUser(User user) {
		return userService.create(user);
	}

	@Override
	public User updateUser(User user) {
		return userService.update(user);
	}

	@Override
	public boolean deleteUser(long userId) {
		return userService.remove(userId);
	}

	@Override
	public Ticket bookTicket(long userId, long eventId, int place, Category category) {
		return ticketService.create(userId, eventId, place, category);
	}

	@Override
	public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
		return ticketService.getTicketsByUser(user, countOffset(pageSize, pageNum), pageSize);
	}

	@Override
	public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
		return ticketService.getTicketsByEvent(event, countOffset(pageSize, pageNum), pageSize);
	}

	@Override
	public boolean cancelTicket(long ticketId) {
		return ticketService.remove(ticketId);
	}

	private int countOffset(int pageSize, int pageNum) {
		return pageSize * (pageNum - 1);
	}
}
