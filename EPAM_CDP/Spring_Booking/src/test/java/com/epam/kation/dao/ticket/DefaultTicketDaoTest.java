package com.epam.kation.dao.ticket;

import static com.google.common.collect.Iterables.getOnlyElement;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.kation.dao.storage.Storage;
import com.epam.kation.model.event.Event;
import com.epam.kation.model.ticket.Ticket;
import com.epam.kation.model.user.User;

@RunWith(MockitoJUnitRunner.class)
public class DefaultTicketDaoTest {

	private static final int OFFSET = 1;
	private static final int LIMIT = 2;
	private static final Long ID = 3L;
	private static final Long USER_ID = 4L;
	private static final Long EVENT_ID = 5L;
	private static final int PLACE = 6;
	private static final boolean REMOVE_RESULT = true;
	private static final Ticket.Category CATEGORY = Ticket.Category.STANDARD;

	@Mock
	private Storage storage;

	@Mock
	private Ticket ticket;

	@Mock
	private Event event;

	@Mock
	private User user;

	@Mock
	private Ticket updatedTicket;

	@Mock
	private Ticket newTicket;

	@InjectMocks
	private TicketDao dao = new DefaultTicketDao() {

		@Override
		public Ticket newTicket() {
			return newTicket;
		}
	};

	@Test
	public void shouldCallCreateOnStorageWhenCreate() {
		InOrder order = inOrder(newTicket, storage);
		when(storage.create(newTicket)).thenReturn(ticket);

		Ticket actualUpdatedTicket = dao.create(USER_ID, EVENT_ID, PLACE, CATEGORY);

		order.verify(newTicket).setUserId(USER_ID);
		order.verify(newTicket).setEventId(EVENT_ID);
		order.verify(newTicket).setPlace(PLACE);
		order.verify(newTicket).setCategory(CATEGORY);
		order.verify(storage).create(newTicket);

		assertEquals(ticket, actualUpdatedTicket);
	}

	@Test
	public void shouldReturnEmptyWhenGetTickets() {
		InOrder order = inOrder(newTicket, storage);
		when(storage.getByExample(newTicket)).thenReturn(emptyList());

		Optional<Ticket> ticket = dao.getTicket(EVENT_ID, PLACE);

		order.verify(newTicket).setEventId(EVENT_ID);
		order.verify(newTicket).setPlace(PLACE);
		order.verify(storage).getByExample(newTicket);

		assertFalse(ticket.isPresent());
	}

	@Test
	public void shouldCallGetByExampleOnStorageWhenGetTickets() {
		InOrder order = inOrder(newTicket, storage);
		when(storage.getByExample(newTicket)).thenReturn(newArrayList(ticket));

		Optional<Ticket> actualTicket = dao.getTicket(EVENT_ID, PLACE);

		order.verify(newTicket).setEventId(EVENT_ID);
		order.verify(newTicket).setPlace(PLACE);
		order.verify(storage).getByExample(newTicket);

		assertEquals(ticket, actualTicket.get());
	}

	@Test
	public void shouldCallGetByExampleOnStorageWhenGetTicketsByUser() {
		InOrder order = inOrder(newTicket, storage);
		when(user.getId()).thenReturn(USER_ID);
		when(storage.getByExample(newTicket, OFFSET, LIMIT)).thenReturn(newArrayList(ticket));

		List<Ticket> tickets = dao.getTicketsByUser(user, OFFSET, LIMIT);

		order.verify(newTicket).setUserId(USER_ID);
		order.verify(storage).getByExample(newTicket, OFFSET, LIMIT);

		assertEquals(ticket, getOnlyElement(tickets));
	}

	@Test
	public void shouldCallGetByExampleOnStorageWhenGetTicketsByEvent() {
		InOrder order = inOrder(newTicket, storage);
		when(event.getId()).thenReturn(EVENT_ID);
		when(storage.getByExample(newTicket, OFFSET, LIMIT)).thenReturn(newArrayList(ticket));

		List<Ticket> tickets = dao.getTicketsByEvent(event, OFFSET, LIMIT);

		order.verify(newTicket).setEventId(EVENT_ID);
		order.verify(storage).getByExample(newTicket, OFFSET, LIMIT);

		assertEquals(ticket, getOnlyElement(tickets));
	}

	@Test
	public void shouldCallRemoveByExampleOnStorageWhenRemove() {
		InOrder order = inOrder(newTicket, storage);
		when(storage.removeByExample(newTicket)).thenReturn(REMOVE_RESULT);

		boolean actualResult = dao.remove(ID);

		order.verify(newTicket).setId(ID);
		order.verify(storage).removeByExample(newTicket);

		assertEquals(REMOVE_RESULT, actualResult);
	}
}
