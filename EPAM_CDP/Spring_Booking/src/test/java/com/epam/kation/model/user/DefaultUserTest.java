package com.epam.kation.model.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class DefaultUserTest {

	private static final Long ID = 0L;
	private static final String NAME = "name";
	private static final String EMAIL = "email";

	private User user = new DefaultUser();

	@Test
	public void shouldReturnNull() {
		assertNull(user.getId());
		assertNull(user.getName());
		assertNull(user.getEmail());
	}

	@Test
	public void shouldSetId() {
		user.setId(ID);

		assertEquals(ID, user.getId());
	}

	@Test
	public void shouldSetName() {
		user.setName(NAME);

		assertEquals(NAME, user.getName());
	}

	@Test
	public void shouldSetEmail() {
		user.setEmail(EMAIL);

		assertEquals(EMAIL, user.getEmail());
	}
}
