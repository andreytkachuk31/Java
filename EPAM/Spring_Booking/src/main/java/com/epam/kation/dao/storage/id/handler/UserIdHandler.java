package com.epam.kation.dao.storage.id.handler;

import com.epam.kation.model.user.User;

public class UserIdHandler extends AbstractIdHandler {

	protected boolean isCorrectType(Object element) {
		return element instanceof User;
	}

	protected Long getIdProtected(Object element) {
		return getUser(element).getId();
	}

	protected void setIdProtected(Object element, Long id) {
		getUser(element).setId(id);
	}

	private User getUser(Object element) {
		return (User) element;
	}
}
