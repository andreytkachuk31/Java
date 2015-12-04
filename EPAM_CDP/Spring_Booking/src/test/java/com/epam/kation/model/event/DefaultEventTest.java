package com.epam.kation.model.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Test;

public class DefaultEventTest {

	private static final Long ID = 0L;
	private static final String TITLE = "title";
	private static final Date DATE = new Date();

	private Event event = new DefaultEvent();

	@Test
	public void shouldReturnNull() {
		assertNull(event.getId());
		assertNull(event.getTitle());
		assertNull(event.getDate());
	}

	@Test
	public void shouldSetId() {
		event.setId(ID);

		assertEquals(ID, event.getId());
	}

	@Test
	public void shouldSetTitle() {
		event.setTitle(TITLE);

		assertEquals(TITLE, event.getTitle());
	}

	@Test
	public void shouldSetDate() {
		event.setDate(DATE);

		assertEquals(DATE, event.getDate());
	}
}
