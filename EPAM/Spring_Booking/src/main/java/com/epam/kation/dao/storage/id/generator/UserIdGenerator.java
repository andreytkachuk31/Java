package com.epam.kation.dao.storage.id.generator;

import com.epam.kation.model.user.User;

public class UserIdGenerator extends AbstractIdGenerator {

	@Override
	protected boolean isIncorrectType(Class<? extends Object> type) {
		return User.class.isAssignableFrom(type);
	}
}
