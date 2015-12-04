package com.epam.kation.dao.storage.clonner;

import com.epam.kation.model.ticket.Ticket;

public abstract class TicketClonner extends AbstractClonner {

	@Override
	protected boolean isCorrectType(Object element) {
		return element instanceof Ticket;
	}

	@Override
	protected Object newInstance() {
		return newTicket();
	}

	@Override
	protected void populate(Object element, Object newElement) {
		Ticket ticket = (Ticket) element;
		Ticket newTicket = (Ticket) newElement;

		newTicket.setId(ticket.getId());
		newTicket.setEventId(ticket.getEventId());
		newTicket.setUserId(ticket.getUserId());
		newTicket.setCategory(ticket.getCategory());
		newTicket.setPlace(ticket.getPlace());
	}

	public abstract Ticket newTicket();
}
