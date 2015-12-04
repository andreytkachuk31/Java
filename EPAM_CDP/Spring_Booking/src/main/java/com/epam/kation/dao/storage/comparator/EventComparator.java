package com.epam.kation.dao.storage.comparator;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import java.util.Date;

import com.epam.kation.model.event.Event;

public class EventComparator extends AbstractComparator {

	@Override
	protected boolean isCorrectType(Object element) {
		return element instanceof Event;
	}

	@Override
	protected boolean compareAfterValidation(Object element1, Object element2) {
		Event event1 = (Event) element1;
		Event event2 = (Event) element2;

		boolean compareId = compareId(event1, event2);
		boolean compareTitle = compareTitle(event1, event2);
		boolean compareDate = compareDate(event1, event2);

		return compareId && compareTitle && compareDate;
	}

	private boolean compareDate(Event event1, Event event2) {
		Date date1 = event1.getDate();
		Date date2 = defaultIfNull(event2.getDate(), date1);

		return compareWithNulls(date1, date2, Date::equals);
	}

	private boolean compareTitle(Event event1, Event event2) {
		String title1 = event1.getTitle();
		String title2 = defaultIfNull(event2.getTitle(), title1);

		return compareWithNulls(title1, title2, String::contains);
	}

	private boolean compareId(Event event1, Event event2) {
		Long id1 = event1.getId();
		Long id2 = defaultIfNull(event2.getId(), id1);

		return compareWithNulls(id1, id2, Long::equals);
	}
}
