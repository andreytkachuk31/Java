package com.epam.kation.service.ticket;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.epam.kation.dao.ticket.TicketDao;
import com.epam.kation.model.event.Event;
import com.epam.kation.model.ticket.Ticket;
import com.epam.kation.model.ticket.Ticket.Category;
import com.epam.kation.model.user.User;

public class DefaultTicketService implements TicketService {

	private final static Logger LOG = Logger.getLogger(DefaultTicketService.class);

	private TicketDao ticketDao;

	@Override
	public synchronized Ticket create(Long userId, Long eventId, Integer place, Category category) {
		Optional<Ticket> ticket = ticketDao.getTicket(eventId, place);

		if (ticket.isPresent()) {
			LOG.info("Tried to book already booked ticket: " + eventId + "/" + place);
			throw new IllegalArgumentException();
		}

		return ticketDao.create(userId, eventId, place, category);
	}

	@Override
	public List<Ticket> getTicketsByUser(User user, Integer offset, Integer limit) {
		return ticketDao.getTicketsByUser(user, offset, limit);
	}

	@Override
	public List<Ticket> getTicketsByEvent(Event event, Integer offset, Integer limit) {
		return ticketDao.getTicketsByEvent(event, offset, limit);
	}

	@Override
	public boolean remove(Long id) {
		return ticketDao.remove(id);
	}

	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}
}
