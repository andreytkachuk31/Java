package com.epam.kation.dao.storage.id.handler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.kation.model.event.Event;

@RunWith(MockitoJUnitRunner.class)
public class EventIdHandlerTest extends AbstractHandlerTest {

	@Mock
	private Event event;
	
	private IdHandler idHandler = new EventIdHandler();

	@Override
	protected Object getType() {
		return event;
	}

	@Override
	protected void setUpId(Long id) {
		when(event.getId()).thenReturn(id);
	}

	@Override
	protected void verifySetId(Long id) {
		verify(event).setId(ID);
	}

	@Override
	protected IdHandler getIdHandler() {
		return idHandler;
	}
}
