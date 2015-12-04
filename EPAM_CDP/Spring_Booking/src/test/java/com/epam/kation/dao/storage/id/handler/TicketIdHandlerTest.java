package com.epam.kation.dao.storage.id.handler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.kation.model.ticket.Ticket;

@RunWith(MockitoJUnitRunner.class)
public class TicketIdHandlerTest extends AbstractHandlerTest {

	@Mock
	private Ticket ticket;

	private IdHandler idHandler = new TicketIdHandler();

	@Override
	protected Object getType() {
		return ticket;
	}

	@Override
	protected void setUpId(Long id) {
		when(ticket.getId()).thenReturn(id);
	}

	@Override
	protected void verifySetId(Long id) {
		verify(ticket).setId(ID);
	}

	@Override
	protected IdHandler getIdHandler() {
		return idHandler;
	}
}
