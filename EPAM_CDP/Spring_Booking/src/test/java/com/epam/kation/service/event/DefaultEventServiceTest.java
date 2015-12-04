package com.epam.kation.service.event;

import static com.google.common.collect.Iterables.getOnlyElement;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.kation.dao.event.EventDao;
import com.epam.kation.model.event.Event;

@RunWith(MockitoJUnitRunner.class)
public class DefaultEventServiceTest {

	private static final Long ID = 1L;
	private static final int OFFSET = 2;
	private static final int LIMIT = 3;
	private static final Boolean REMOVE_RESULT = Boolean.TRUE;
	private static final String TITLE = "title";
	private static final Date DATE = new Date();

	@Mock
	private EventDao eventDao;

	@Mock
	private Event event;

	@Mock
	private Event resultEvent;

	@Mock
	private Event createdEvent;

	@Mock
	private Event updatedEvent;

	@Mock
	private Event newEvent;

	@InjectMocks
	private EventService service = new DefaultEventService();

	@Test
	public void shouldCallCreateOnEventDaoWhenCreateEvent() {
		when(eventDao.create(event)).thenReturn(createdEvent);

		Event actualCreateEvent = service.create(event);

		verify(eventDao).create(event);

		assertEquals(createdEvent, actualCreateEvent);
	}

	@Test
	public void shouldCallUpdateOnEventDaoWhenUpdateEvent() {
		when(eventDao.update(event)).thenReturn(updatedEvent);

		Event actualUpdatedEvent = service.update(event);

		verify(eventDao).update(event);

		assertEquals(updatedEvent, actualUpdatedEvent);
	}

	@Test
	public void shouldReturnOptionalWhenGetById() {
		when(eventDao.get(ID)).thenReturn(of(event));

		Optional<Event> actualResult = service.get(ID);

		verify(eventDao).get(ID);

		assertEquals(event, actualResult.get());
	}

	@Test
	public void shouldReturnResultListWhenGetEventByTitle() {
		when(eventDao.getEventsByTitle(TITLE, OFFSET, LIMIT)).thenReturn(newArrayList(resultEvent));

		List<Event> actualResult = service.getEventsByTitle(TITLE, OFFSET, LIMIT);

		verify(eventDao).getEventsByTitle(TITLE, OFFSET, LIMIT);

		assertEquals(resultEvent, getOnlyElement(actualResult));
	}

	@Test
	public void shouldReturnResultListWhenGetEventForDay() {
		when(eventDao.getEventsForDay(DATE, OFFSET, LIMIT)).thenReturn(newArrayList(resultEvent));

		List<Event> actualResult = service.getEventsForDay(DATE, OFFSET, LIMIT);

		verify(eventDao).getEventsForDay(DATE, OFFSET, LIMIT);

		assertEquals(resultEvent, getOnlyElement(actualResult));
	}

	@Test
	public void shouldRemoveWhenRemove() {
		when(eventDao.remove(ID)).thenReturn(REMOVE_RESULT);

		boolean actualResult = service.remove(ID);

		verify(eventDao).remove(ID);

		assertEquals(REMOVE_RESULT, actualResult);
	}
}
