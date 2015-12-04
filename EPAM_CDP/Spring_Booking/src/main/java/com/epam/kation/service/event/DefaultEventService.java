package com.epam.kation.service.event;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.epam.kation.dao.event.EventDao;
import com.epam.kation.model.event.Event;

public class DefaultEventService implements EventService {

	private EventDao eventDao;

	@Override
	public Event create(Event event) {
		return eventDao.create(event);
	}

	@Override
	public Optional<Event> get(Long id) {
		return eventDao.get(id);
	}

	@Override
	public List<Event> getEventsByTitle(String title, Integer offset, Integer limit) {
		return eventDao.getEventsByTitle(title, offset, limit);
	}

	@Override
	public List<Event> getEventsForDay(Date day, Integer offset, Integer limit) {
		return eventDao.getEventsForDay(day, offset, limit);
	}

	@Override
	public Event update(Event event) {
		return eventDao.update(event);
	}

	@Override
	public boolean remove(Long id) {
		return eventDao.remove(id);
	}

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
}
