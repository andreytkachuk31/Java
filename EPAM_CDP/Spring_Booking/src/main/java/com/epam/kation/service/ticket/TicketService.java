package com.epam.kation.service.ticket;

import java.util.List;

import com.epam.kation.model.event.Event;
import com.epam.kation.model.ticket.Ticket;
import com.epam.kation.model.user.User;

public interface TicketService {

	Ticket create(Long userId, Long eventId, Integer place, Ticket.Category category);

	List<Ticket> getTicketsByUser(User user, Integer offset, Integer limit);

	List<Ticket> getTicketsByEvent(Event event, Integer offset, Integer limit);

	boolean remove(Long id);
}
