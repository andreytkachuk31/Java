package com.epam.kation.dao.storage.clonner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.kation.model.ticket.Ticket;

@RunWith(MockitoJUnitRunner.class)
public class TicketClonnerTest extends AbstractClonnerTest {

	private static final Long ID = 0L;
	private static final Long EVENT_ID = 1L;
	private static final Long USER_ID = 2L;
	private static final Ticket.Category CATEGORY = Ticket.Category.STANDARD;
	private static final Integer PLACE = 2;

	@Mock
	private Ticket ticket;

	@Mock
	private Ticket newTicket;

	private TicketClonner clonner = new TicketClonner() {

		@Override
		public Ticket newTicket() {
			return newTicket;
		}
	};

	@Override
	protected Object getElement() {
		when(ticket.getId()).thenReturn(ID);
		when(ticket.getEventId()).thenReturn(EVENT_ID);
		when(ticket.getUserId()).thenReturn(USER_ID);
		when(ticket.getCategory()).thenReturn(CATEGORY);
		when(ticket.getPlace()).thenReturn(PLACE);

		return ticket;
	}

	@Override
	protected Clonner getClonner() {
		return clonner;
	}

	@Override
	protected void verifyClonned() {
		verify(newTicket).setId(ID);
		verify(newTicket).setEventId(EVENT_ID);
		verify(newTicket).setUserId(USER_ID);
		verify(newTicket).setCategory(CATEGORY);
		verify(newTicket).setPlace(PLACE);
	}

	@Override
	protected void assertCloneElement(Object clonnedElement) {
		assertEquals(newTicket, clonnedElement);
	}
}
