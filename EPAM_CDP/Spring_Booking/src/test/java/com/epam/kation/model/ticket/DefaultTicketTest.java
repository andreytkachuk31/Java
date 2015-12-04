package com.epam.kation.model.ticket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class DefaultTicketTest {

	private static final Long ID = 0L;
	private static final Long EVENT_ID = 1L;
	private static final Long USER_ID = 3L;
	private static final Ticket.Category CATEGORY = Ticket.Category.STANDARD;
	private static final Integer PLACE = 4;

	private Ticket ticket = new DefaultTicket();

	@Test
	public void shouldReturnNull() {
		assertNull(ticket.getId());
		assertNull(ticket.getEventId());
		assertNull(ticket.getUserId());
		assertNull(ticket.getCategory());
		assertNull(ticket.getPlace());
	}

	@Test
	public void shouldSetId() {
		ticket.setId(ID);

		assertEquals(ID, ticket.getId());
	}

	@Test
	public void shouldSetEventId() {
		ticket.setEventId(EVENT_ID);

		assertEquals(EVENT_ID, ticket.getEventId());
	}

	@Test
	public void shouldSetUserId() {
		ticket.setUserId(USER_ID);

		assertEquals(USER_ID, ticket.getUserId());
	}

	@Test
	public void shouldSetCategory() {
		ticket.setCategory(CATEGORY);

		assertEquals(CATEGORY, ticket.getCategory());
	}

	@Test
	public void shouldSetPlace() {
		ticket.setPlace(PLACE);

		assertEquals(PLACE, ticket.getPlace());
	}
}
