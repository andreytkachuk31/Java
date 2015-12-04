package com.epam.kation.dao.storage.id.generator;

import com.epam.kation.model.event.Event;

public class EventIdGeneratorTest extends AbstractIdGeneratorTest {

	private IdGenerator idGenerator = new EventIdGenerator();

	@Override
	protected Class<? extends Object> getType() {
		return Event.class;
	}

	@Override
	protected IdGenerator getIdGenerator() {
		return idGenerator;
	}
}
