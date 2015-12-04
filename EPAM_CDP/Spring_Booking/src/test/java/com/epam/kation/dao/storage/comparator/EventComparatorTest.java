package com.epam.kation.dao.storage.comparator;

import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.kation.model.event.Event;

@RunWith(MockitoJUnitRunner.class)
public class EventComparatorTest extends AbstractComparatorTest {

	private static final Long ZERO_ID = 0L;
	private static final Long ONE_ID = 1L;

	private static final String FIRST_TITLE = "firstTitle";
	private static final String LAST_TITLE = "secondTitle";

	private static final Date FIRST_DATE = new Date(0);
	private static final Date LAST_DATE = new Date(1);

	@Mock
	private Event event1;

	@Mock
	private Event event2;

	private Comparator comparator = new EventComparator();

	@Test
	public void shouldReturnTrueWhenCompareWithTwoNullEvents() {
		setUpEvent1(null, null, null);
		setUpEvent2(null, null, null);

		assertCompareEquals();
	}

	@Test
	public void shouldReturnTrueWhenCompareWithOneFullAndOneNullObjects() {
		setUpEvent1(ZERO_ID, FIRST_TITLE, FIRST_DATE);
		setUpEvent2(null, null, null);

		assertCompareEquals();
	}

	@Test
	public void shouldReturnFalseWhenCompareWithOneNullAndOneFullObjects() {
		setUpEvent1(null, null, null);
		setUpEvent2(ZERO_ID, FIRST_TITLE, FIRST_DATE);

		assertCompareNotEquals();
	}

	@Test
	public void shouldReturnFalseWhenCompareWithOneSmallerIdAndOneBIdObjects() {
		setUpEvent1(ZERO_ID, null, null);
		setUpEvent2(ONE_ID, null, null);

		assertCompareNotEquals();
	}

	@Test
	public void shouldReturnTrueWhenCompareWithOneBiggerIdAndOneBiggerIdObjects() {
		setUpEvent1(ONE_ID, null, null);
		setUpEvent2(ONE_ID, null, null);

		assertCompareEquals();
	}

	@Test
	public void shouldReturnFalseWhenCompareWithOneBiggerIdAndOneSmallerIdObjects() {
		setUpEvent1(ONE_ID, null, null);
		setUpEvent2(ZERO_ID, null, null);

		assertCompareNotEquals();
	}

	@Test
	public void shouldReturnFalseWhenCompareWithOneFirstTitleAndOneLastTitleObjects() {
		setUpEvent1(null, FIRST_TITLE, null);
		setUpEvent2(null, LAST_TITLE, null);

		assertCompareNotEquals();
	}

	@Test
	public void shouldReturnTrueWhenCompareWithOneFirstLastFirstTitleAndOneLastTitleObjects() {
		setUpEvent1(null, FIRST_TITLE + LAST_TITLE + FIRST_TITLE, null);
		setUpEvent2(null, LAST_TITLE, null);

		assertCompareEquals();
	}

	@Test
	public void shouldReturnFalseWhenCompareWithOneFirstDateAndOneLastDateObjects() {
		setUpEvent1(null, null, FIRST_DATE);
		setUpEvent2(null, null, LAST_DATE);

		assertCompareNotEquals();
	}

	@Test
	public void shouldReturnTrueWhenCompareWithOneFirstDateAndOneFirstDateObjects() {
		setUpEvent1(null, null, FIRST_DATE);
		setUpEvent2(null, null, FIRST_DATE);

		assertCompareEquals();
	}

	@Test
	public void shouldReturnFalseWhenCompareWithOneLastDateAndOneFirstDateObjects() {
		setUpEvent1(null, null, LAST_DATE);
		setUpEvent2(null, null, FIRST_DATE);

		assertCompareNotEquals();
	}

	@Override
	protected Comparator getComparator() {
		return comparator;
	}

	@Override
	protected Object getElement1() {
		return event1;
	}

	@Override
	protected Object getElement2() {
		return event2;
	}

	private void setUpEvent1(Long id, String eventTitle, Date date) {
		setUpEvent(event1, id, eventTitle, date);
	}

	private void setUpEvent2(Long id, String eventTitle, Date date) {
		setUpEvent(event2, id, eventTitle, date);
	}

	private void setUpEvent(Event event, Long id, String eventTitle, Date date) {
		when(event.getId()).thenReturn(id);
		when(event.getTitle()).thenReturn(eventTitle);
		when(event.getDate()).thenReturn(date);
	}
}
