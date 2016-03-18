package com.epam.kation.dao.event;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.epam.kation.model.event.Event;

public interface EventDao {

	Event create(Event event);

	Optional<Event> get(Long id);

	List<Event> getEventsByTitle(String title, Integer offset, Integer limit);

	List<Event> getEventsForDay(Date day, Integer offset, Integer limit);

	Event update(Event event);

	boolean remove(Long id);
}
