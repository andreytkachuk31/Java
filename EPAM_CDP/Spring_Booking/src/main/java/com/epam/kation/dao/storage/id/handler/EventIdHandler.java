package com.epam.kation.dao.storage.id.handler;

import com.epam.kation.model.event.Event;

public class EventIdHandler extends AbstractIdHandler {

	protected boolean isCorrectType(Object element) {
		return element instanceof Event;
	}

	protected Long getIdProtected(Object element) {
		return getEvent(element).getId();
	}

	protected void setIdProtected(Object element, Long id) {
		getEvent(element).setId(id);
	}

	private Event getEvent(Object element) {
		return (Event) element;
	}
}
