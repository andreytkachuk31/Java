package com.epam.kation.dao.storage.id.generator;

import com.epam.kation.model.event.Event;

public class EventIdGenerator extends AbstractIdGenerator {

	@Override
	protected boolean isIncorrectType(Class<? extends Object> type) {
		return Event.class.isAssignableFrom(type);
	}
}
