package com.epam.kation.dao.storage.comparator;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import com.epam.kation.model.ticket.Ticket;

public class TicketComparator extends AbstractComparator {

	@Override
	protected boolean isCorrectType(Object element) {
		return element instanceof Ticket;
	}

	@Override
	protected boolean compareAfterValidation(Object element1, Object element2) {
		Ticket ticket1 = (Ticket) element1;
		Ticket ticket2 = (Ticket) element2;

		boolean compareId = compareId(ticket1, ticket2);
		boolean compareEventId = compareEventId(ticket1, ticket2);
		boolean compareUserId = compareUserId(ticket1, ticket2);
		boolean compareCategory = compareCategory(ticket1, ticket2);
		boolean comparePlace = comparePlace(ticket1, ticket2);

		return compareId && compareEventId && compareUserId && compareCategory && comparePlace;
	}

	private boolean compareId(Ticket ticket1, Ticket ticket2) {
		Long id1 = ticket1.getId();
		Long id2 = defaultIfNull(ticket2.getId(), id1);

		return compareWithNulls(id1, id2, Long::equals);
	}

	private boolean compareEventId(Ticket ticket1, Ticket ticket2) {
		Long eventId1 = ticket1.getEventId();
		Long eventId2 = defaultIfNull(ticket2.getEventId(), eventId1);

		return compareWithNulls(eventId1, eventId2, Long::equals);
	}

	private boolean compareUserId(Ticket ticket1, Ticket ticket2) {
		Long userId1 = ticket1.getUserId();
		Long userId2 = defaultIfNull(ticket2.getUserId(), userId1);

		return compareWithNulls(userId1, userId2, Long::equals);
	}

	private boolean compareCategory(Ticket ticket1, Ticket ticket2) {
		Ticket.Category category1 = ticket1.getCategory();
		Ticket.Category category2 = defaultIfNull(ticket2.getCategory(), category1);

		return compareWithNulls(category1, category2, Ticket.Category::equals);
	}

	private boolean comparePlace(Ticket ticket1, Ticket ticket2) {
		Integer place1 = ticket1.getPlace();
		Integer place2 = defaultIfNull(ticket2.getPlace(), place1);

		return compareWithNulls(place1, place2, Integer::equals);
	}
}
