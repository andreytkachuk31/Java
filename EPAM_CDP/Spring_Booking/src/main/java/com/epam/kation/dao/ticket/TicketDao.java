package com.epam.kation.dao.ticket;

import java.util.List;
import java.util.Optional;

import com.epam.kation.model.event.Event;
import com.epam.kation.model.ticket.Ticket;
import com.epam.kation.model.user.User;

public interface TicketDao {

	Ticket create(Long userId, Long eventId, Integer place, Ticket.Category category);

	Optional<Ticket> getTicket(Long eventId, Integer place);

	List<Ticket> getTicketsByUser(User user, Integer offset, Integer limit);

	List<Ticket> getTicketsByEvent(Event event, Integer offset, Integer limit);

	boolean remove(Long id);
}
