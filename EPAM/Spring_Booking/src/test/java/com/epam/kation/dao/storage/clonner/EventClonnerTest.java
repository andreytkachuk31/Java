package com.epam.kation.dao.storage.clonner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.kation.model.event.Event;

@RunWith(MockitoJUnitRunner.class)
public class EventClonnerTest extends AbstractClonnerTest {

	private static final long ID = 0L;
	private static final String TITLE = "title";
	private static final Date DATE = new Date();

	@Mock
	private Event event;

	@Mock
	private Event newEvent;

	private EventClonner clonner = new EventClonner() {

		@Override
		public Event newEvent() {
			return newEvent;
		}
	};

	@Override
	protected Object getElement() {
		when(event.getId()).thenReturn(ID);
		when(event.getTitle()).thenReturn(TITLE);
		when(event.getDate()).thenReturn(DATE);

		return event;
	}

	@Override
	protected Clonner getClonner() {
		return clonner;
	}

	@Override
	protected void verifyClonned() {
		verify(newEvent).setId(ID);
		verify(newEvent).setTitle(TITLE);
		verify(newEvent).setDate(DATE);
	}

	@Override
	protected void assertCloneElement(Object clonnedElement) {
		assertEquals(newEvent, clonnedElement);
	}
}
