package com.epam.kation.dao.event;

import static com.google.common.collect.Iterables.getOnlyElement;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
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

@RunWith(MockitoJUnitRunner.class)
public class DefaultEventDaoTest {

	private static final Long ID = 1L;
	private static final int OFFSET = 2;
	private static final int LIMIT = 3;
	private static final Date DATE = new Date();
	private static final boolean REMOVE_RESULT = true;
	private static final String TITLE = "title";

	@Mock
	private Storage storage;

	@Mock
	private Event event;

	@Mock
	private Event updatedEvent;

	@Mock
	private Event newEvent;

	@InjectMocks
	private EventDao dao = new DefaultEventDao() {

		@Override
		public Event newEvent() {
			return newEvent;
		}
	};

	@Test
	public void shouldCallCreateOnStorageWhenCreate() {
		when(storage.create(event)).thenReturn(updatedEvent);

		Event actualUpdatedEvent = dao.create(event);

		verify(storage).create(event);

		assertEquals(updatedEvent, actualUpdatedEvent);
	}

	@Test
	public void shouldCallUpdateOnStorageWhenUpdate() {
		when(storage.update(event)).thenReturn(updatedEvent);

		Event actualUpdatedEvent = dao.update(event);

		verify(storage).update(event);

		assertEquals(updatedEvent, actualUpdatedEvent);
	}

	@Test
	public void shouldReturnEmptyWhenGetByIdWithUnexistingElement() {
		InOrder order = inOrder(newEvent, storage);
		when(storage.getByExample(newEvent)).thenReturn(emptyList());

		Optional<Event> actualEvent = dao.get(ID);

		order.verify(newEvent).setId(ID);
		order.verify(storage).getByExample(newEvent);

		assertFalse(actualEvent.isPresent());
	}

	@Test
	public void shouldReturnElementWhenGetByIdWithExistingElement() {
		InOrder order = inOrder(newEvent, storage);
		when(storage.getByExample(newEvent)).thenReturn(newArrayList(event));

		Optional<Event> actualEvent = dao.get(ID);

		order.verify(newEvent).setId(ID);
		order.verify(storage).getByExample(newEvent);

		assertEquals(event, actualEvent.get());
	}

	@Test
	public void shouldCallGetByExampleOnStorageWhenGetByTitle() {
		InOrder order = inOrder(newEvent, storage);
		when(storage.getByExample(newEvent, OFFSET, LIMIT)).thenReturn(newArrayList(event));

		List<Event> actualEvents = dao.getEventsByTitle(TITLE, OFFSET, LIMIT);

		order.verify(newEvent).setTitle(TITLE);
		order.verify(storage).getByExample(newEvent, OFFSET, LIMIT);

		assertEquals(event, getOnlyElement(actualEvents));
	}

	@Test
	public void shouldCallGetByExampleOnStorageWhenGetForDay() {
		InOrder order = inOrder(newEvent, storage);
		when(storage.getByExample(newEvent, OFFSET, LIMIT)).thenReturn(newArrayList(event));

		List<Event> actualEvents = dao.getEventsForDay(DATE, OFFSET, LIMIT);

		order.verify(newEvent).setDate(DATE);
		order.verify(storage).getByExample(newEvent, OFFSET, LIMIT);

		assertEquals(event, getOnlyElement(actualEvents));
	}

	@Test
	public void shouldCallRemoveByExampleOnStorageWhenRemove() {
		InOrder order = inOrder(newEvent, storage);
		when(storage.removeByExample(newEvent)).thenReturn(REMOVE_RESULT);

		boolean actualResult = dao.remove(ID);

		order.verify(newEvent).setId(ID);
		order.verify(storage).removeByExample(newEvent);

		assertEquals(REMOVE_RESULT, actualResult);
	}
}
