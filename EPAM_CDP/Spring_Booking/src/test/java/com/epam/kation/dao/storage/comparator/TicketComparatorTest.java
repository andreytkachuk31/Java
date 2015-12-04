package com.epam.kation.dao.storage.comparator;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.kation.model.ticket.Ticket;

@RunWith(MockitoJUnitRunner.class)
public class TicketComparatorTest extends AbstractComparatorTest {

	private static final Long ZERO_ID = 0L;
	private static final Long ONE_ID = 1L;

	private static final Ticket.Category FIRST_CATEGORY = Ticket.Category.STANDARD;
	private static final Ticket.Category LAST_CATEGORY = Ticket.Category.PREMIUM;

	private static final Integer ZERO_PLACE = 0;
	private static final Integer ONE_PLACE = 1;

	@Mock
	private Ticket ticket1;

	@Mock
	private Ticket ticket2;

	private Comparator comparator = new TicketComparator();

	@Test
	public void shouldReturnTrueWhenCompareWithTwoNullTickets() {
		setUpTicket1(null, null, null, null, null);
		setUpTicket2(null, null, null, null, null);

		assertCompareEquals();
	}

	@Test
	public void shouldReturnTrueWhenCompareWithOneFullAndOneNullObjects() {
		setUpTicket1(ZERO_ID, ZERO_ID, ZERO_ID, FIRST_CATEGORY, ZERO_PLACE);
		setUpTicket2(null, null, null, null, null);

		assertCompareEquals();
	}

	@Test
	public void shoulReturnFalseWhenCompareWithOneNullAndOneFullObjects() {
		setUpTicket1(null, null, null, null, null);
		setUpTicket2(ZERO_ID, ZERO_ID, ZERO_ID, FIRST_CATEGORY, ZERO_PLACE);

		assertCompareNotEquals();
	}

	@Test
	public void shouldReturnFalseWhenCompareWithOneSmallerIdAndOneBIdObjects() {
		setUpTicket1(ZERO_ID, null, null, null, null);
		setUpTicket2(ONE_ID, null, null, null, null);

		assertCompareNotEquals();
	}

	@Test
	public void shouldReturnTrueWhenCompareWithOneBiggerIdAndOneBiggerIdObjects() {
		setUpTicket1(ONE_ID, null, null, null, null);
		setUpTicket2(ONE_ID, null, null, null, null);

		assertCompareEquals();
	}

	@Test
	public void shouldReturnFalseWhenCompareWithOneSmallerEventIdAndOneBEventIdObjects() {
		setUpTicket1(null, ZERO_ID, null, null, null);
		setUpTicket2(null, ONE_ID, null, null, null);

		assertCompareNotEquals();
	}

	@Test
	public void shouldReturnTrueWhenCompareWithOneBiggerEventIdAndOneBiggerEventIdObjects() {
		setUpTicket1(null, ONE_ID, null, null, null);
		setUpTicket2(null, ONE_ID, null, null, null);

		assertCompareEquals();
	}

	@Test
	public void shouldReturnFalseWhenCompareWithOneSmallerUserIdAndOneBUserIdObjects() {
		setUpTicket1(null, null, ZERO_ID, null, null);
		setUpTicket2(null, null, ONE_ID, null, null);

		assertCompareNotEquals();
	}

	@Test
	public void shouldReturnTrueWhenCompareWithOneBiggerUserIdAndOneBiggerUserIdObjects() {
		setUpTicket1(null, null, ONE_ID, null, null);
		setUpTicket2(null, null, ONE_ID, null, null);

		assertCompareEquals();
	}

	@Test
	public void shouldReturnFalseWhenCompareWithOneFirstCategoryAndOneLastCategoryObjects() {
		setUpTicket1(null, null, null, FIRST_CATEGORY, null);
		setUpTicket2(null, null, null, LAST_CATEGORY, null);

		assertCompareNotEquals();
	}

	@Test
	public void shouldReturnTrueWhenCompareWithOneFirstCategoryAndOneFirtsCategoryObjects() {
		setUpTicket1(null, null, null, FIRST_CATEGORY, null);
		setUpTicket2(null, null, null, FIRST_CATEGORY, null);

		assertCompareEquals();
	}

	@Test
	public void shouldReturnFalseWhenCompareWithZeroPlaceAndOneOnePlaceObjects() {
		setUpTicket1(null, null, null, null, ZERO_PLACE);
		setUpTicket2(null, null, null, null, ONE_PLACE);

		assertCompareNotEquals();
	}

	@Test
	public void shouldReturnTrueWhenCompareWithOnePlaceAndOnePlaceObjects() {
		setUpTicket1(null, null, null, null, ONE_PLACE);
		setUpTicket2(null, null, null, null, ONE_PLACE);

		assertCompareEquals();
	}

	@Override
	protected Comparator getComparator() {
		return comparator;
	}

	@Override
	protected Object getElement1() {
		return ticket1;
	}

	@Override
	protected Object getElement2() {
		return ticket2;
	}

	private void setUpTicket1(Long id, Long eventId, Long userId, Ticket.Category category, Integer place) {
		setUpTicket(ticket1, id, eventId, userId, category, place);
	}

	private void setUpTicket2(Long id, Long eventId, Long userId, Ticket.Category category, Integer place) {
		setUpTicket(ticket2, id, eventId, userId, category, place);
	}

	private void setUpTicket(Ticket ticket, Long id, Long eventId, Long userId, Ticket.Category category, Integer place) {
		when(ticket.getId()).thenReturn(id);
		when(ticket.getEventId()).thenReturn(eventId);
		when(ticket.getUserId()).thenReturn(userId);
		when(ticket.getCategory()).thenReturn(category);
		when(ticket.getPlace()).thenReturn(place);
	}
}
