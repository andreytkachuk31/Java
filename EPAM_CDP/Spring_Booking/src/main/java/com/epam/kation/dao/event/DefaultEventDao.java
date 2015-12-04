package com.epam.kation.dao.event;

import static com.google.common.collect.Iterables.getOnlyElement;
import static java.util.Optional.empty;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.epam.kation.dao.storage.Storage;
import com.epam.kation.model.event.Event;

public abstract class DefaultEventDao implements EventDao {

	private Storage storage;

	@Override
	public Event create(Event event) {
		return storage.create(event);
	}

	@Override
	public Event update(Event event) {
		return storage.update(event);
	}

	@Override
	public Optional<Event> get(Long id) {
		Event event = newEvent();

		event.setId(id);

		List<Event> events = storage.getByExample(event);

		if (events.isEmpty())
			return empty();

		return Optional.of(getOnlyElement(events));
	}

	@Override
	public List<Event> getEventsByTitle(String title, Integer offset, Integer limit) {
		Event event = newEvent();

		event.setTitle(title);

		return storage.getByExample(event, offset, limit);
	}

	@Override
	public List<Event> getEventsForDay(Date day, Integer offset, Integer limit) {
		Event event = newEvent();

		event.setDate(day);

		return storage.getByExample(event, offset, limit);
	}

	@Override
	public boolean remove(Long id) {
		Event event = newEvent();

		event.setId(id);

		return storage.removeByExample(event);
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public abstract Event newEvent();
}
