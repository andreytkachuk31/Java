package com.epam.kation.dao.ticket;

import static com.google.common.collect.Iterables.getOnlyElement;
import static java.util.Optional.empty;

import java.util.List;
import java.util.Optional;

import com.epam.kation.dao.storage.Storage;
import com.epam.kation.model.event.Event;
import com.epam.kation.model.ticket.Ticket;
import com.epam.kation.model.ticket.Ticket.Category;
import com.epam.kation.model.user.User;

public abstract class DefaultTicketDao implements TicketDao {

	private Storage storage;

	@Override
	public Ticket create(Long userId, Long eventId, Integer place, Category category) {
		Ticket ticket = newTicket();

		ticket.setUserId(userId);
		ticket.setEventId(eventId);
		ticket.setPlace(place);
		ticket.setCategory(category);

		return storage.create(ticket);
	}

	@Override
	public Optional<Ticket> getTicket(Long eventId, Integer place) {
		Ticket ticket = newTicket();

		ticket.setEventId(eventId);
		ticket.setPlace(place);

		List<Ticket> tickets = storage.getByExample(ticket);

		if (tickets.isEmpty())
			return empty();

		return Optional.of(getOnlyElement(tickets));
	}

	@Override
	public List<Ticket> getTicketsByUser(User user, Integer offset, Integer limit) {
		Ticket ticket = newTicket();

		ticket.setUserId(user.getId());

		return storage.getByExample(ticket, offset, limit);
	}

	@Override
	public List<Ticket> getTicketsByEvent(Event event, Integer offset, Integer limit) {
		Ticket ticket = newTicket();

		ticket.setEventId(event.getId());

		return storage.getByExample(ticket, offset, limit);
	}

	@Override
	public boolean remove(Long id) {
		Ticket ticket = newTicket();

		ticket.setId(id);

		return storage.removeByExample(ticket);
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public abstract Ticket newTicket();
}
