package com.epam.kation.dao.storage.id.generator;

import com.epam.kation.model.ticket.Ticket;

public class TicketIdGeneratorTest extends AbstractIdGeneratorTest {

	private IdGenerator idGenerator = new TicketIdGenerator();

	@Override
	protected Class<? extends Object> getType() {
		return Ticket.class;
	}

	@Override
	protected IdGenerator getIdGenerator() {
		return idGenerator;
	}
}
