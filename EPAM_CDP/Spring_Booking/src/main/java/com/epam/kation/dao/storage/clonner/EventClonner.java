package com.epam.kation.dao.storage.clonner;

import com.epam.kation.model.event.Event;

public abstract class EventClonner extends AbstractClonner {

	@Override
	protected boolean isCorrectType(Object element) {
		return element instanceof Event;
	}

	@Override
	protected Object newInstance() {
		return newEvent();
	}

	@Override
	protected void populate(Object element, Object newElement) {
		Event event = (Event) element;
		Event newEvent = (Event) newElement;

		newEvent.setId(event.getId());
		newEvent.setTitle(event.getTitle());
		newEvent.setDate(event.getDate());
	}

	public abstract Event newEvent();
}
