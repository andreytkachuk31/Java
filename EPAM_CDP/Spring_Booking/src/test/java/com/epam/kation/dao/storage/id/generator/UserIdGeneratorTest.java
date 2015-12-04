package com.epam.kation.dao.storage.id.generator;

import com.epam.kation.model.user.User;

public class UserIdGeneratorTest extends AbstractIdGeneratorTest {

	private IdGenerator idGenerator = new UserIdGenerator();

	@Override
	protected Class<? extends Object> getType() {
		return User.class;
	}

	@Override
	protected IdGenerator getIdGenerator() {
		return idGenerator;
	}
}
