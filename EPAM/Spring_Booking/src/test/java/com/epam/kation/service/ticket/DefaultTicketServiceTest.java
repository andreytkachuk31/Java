package com.epam.kation.service.ticket;

import static com.google.common.collect.Iterables.getOnlyElement;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.kation.dao.ticket.TicketDao;
import com.epam.kation.model.event.Event;
import com.epam.kation.model.ticket.Ticket;
import com.epam.kation.model.ticket.Ticket.Category;
import com.epam.kation.model.user.User;

@RunWith(MockitoJUnitRunner.class)
public class DefaultTicketServiceTest {

	private static final long USER_ID = 0L;
	private static final long EVENT_ID = 1L;
	private static final Long ID = 2L;
	private static final int OFFSET = 3;
	private static final int LIMIT = 4;
	private static final int PLACE = 5;
	private static final Category CATEGORY = Ticket.Category.STANDARD;
	private static final Boolean REMOVE_RESULT = Boolean.TRUE;

	@Mock
	private TicketDao ticketDao;

	@Mock
	private Ticket ticket;

	@Mock
	private Ticket resultTicket;

	@Mock
	private Ticket createdTicket;

	@Mock
	private Ticket newTicket;

	@Mock
	private Event event;

	@Mock
	private User user;

	@InjectMocks
	private TicketService service = new DefaultTicketService();

	@Test
	public void shouldCallCreateOrUpdateOnTicketDaoWhenCreateTicket() {
		when(ticketDao.getTicket(EVENT_ID, PLACE)).thenReturn(empty());
		when(ticketDao.create(USER_ID, EVENT_ID, PLACE, CATEGORY)).thenReturn(createdTicket);

		Ticket actualCreateTicket = service.create(USER_ID, EVENT_ID, PLACE, CATEGORY);

		verify(ticketDao).create(USER_ID, EVENT_ID, PLACE, CATEGORY);

		assertEquals(createdTicket, actualCreateTicket);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExaptionWhenCreateAlreadyBookedTicket() {
		when(ticketDao.getTicket(EVENT_ID, PLACE)).thenReturn(of(ticket));

		service.create(USER_ID, EVENT_ID, PLACE, CATEGORY);
	}

	@Test
	public void shouldReturnResultListWhenGetTicketsByUser() {
		when(ticketDao.getTicketsByUser(user, OFFSET, LIMIT)).thenReturn(newArrayList(resultTicket));

		List<Ticket> actualResult = service.getTicketsByUser(user, OFFSET, LIMIT);

		verify(ticketDao).getTicketsByUser(user, OFFSET, LIMIT);

		assertEquals(resultTicket, getOnlyElement(actualResult));
	}

	@Test
	public void shouldReturnResultListWhenGetTicketsByEvent() {
		when(ticketDao.getTicketsByEvent(event, OFFSET, LIMIT)).thenReturn(newArrayList(resultTicket));

		List<Ticket> actualResult = service.getTicketsByEvent(event, OFFSET, LIMIT);

		verify(ticketDao).getTicketsByEvent(event, OFFSET, LIMIT);

		assertEquals(resultTicket, getOnlyElement(actualResult));
	}

	@Test
	public void shouldRemoveWhenRemove() {
		when(ticketDao.remove(ID)).thenReturn(REMOVE_RESULT);

		boolean actualResult = service.remove(ID);

		verify(ticketDao).remove(ID);

		assertEquals(REMOVE_RESULT, actualResult);
	}
}
