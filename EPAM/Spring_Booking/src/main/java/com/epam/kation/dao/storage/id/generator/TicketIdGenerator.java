package com.epam.kation.dao.storage.id.generator;

import com.epam.kation.model.ticket.Ticket;

public class TicketIdGenerator extends AbstractIdGenerator {

	@Override
	protected boolean isIncorrectType(Class<? extends Object> type) {
		return Ticket.class.isAssignableFrom(type);
	}
}
