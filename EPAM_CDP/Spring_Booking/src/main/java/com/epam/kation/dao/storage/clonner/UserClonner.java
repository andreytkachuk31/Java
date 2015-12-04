package com.epam.kation.dao.storage.clonner;

import com.epam.kation.model.user.User;

public abstract class UserClonner extends AbstractClonner {

	@Override
	protected boolean isCorrectType(Object element) {
		return element instanceof User;
	}

	@Override
	protected Object newInstance() {
		return newUser();
	}

	@Override
	protected void populate(Object element, Object newElement) {
		User user = (User) element;
		User newUser = (User) newElement;

		newUser.setId(user.getId());
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
	}

	public abstract User newUser();
}
