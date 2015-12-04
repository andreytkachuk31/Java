package com.epam.kation.dao.storage.id.handler;

import com.epam.kation.model.ticket.Ticket;

public class TicketIdHandler extends AbstractIdHandler {

	protected boolean isCorrectType(Object element) {
		return element instanceof Ticket;
	}

	protected Long getIdProtected(Object element) {
		return getTicket(element).getId();
	}

	protected void setIdProtected(Object element, Long id) {
		getTicket(element).setId(id);
	}

	private Ticket getTicket(Object element) {
		return (Ticket) element;
	}
}