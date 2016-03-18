package com.epam.kation.dao.storage.clonner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.kation.model.user.User;

@RunWith(MockitoJUnitRunner.class)
public class UserClonnerTest extends AbstractClonnerTest {

	private static final Long ID = 0L;
	private static final String NAME = "name";
	private static final String EMAIL = "email";

	@Mock
	private User user;

	@Mock
	private User newUser;

	private UserClonner clonner = new UserClonner() {

		@Override
		public User newUser() {
			return newUser;
		}
	};

	@Override
	protected Object getElement() {
		when(user.getId()).thenReturn(ID);
		when(user.getName()).thenReturn(NAME);
		when(user.getEmail()).thenReturn(EMAIL);

		return user;
	}

	@Override
	protected Clonner getClonner() {
		return clonner;
	}

	@Override
	protected void verifyClonned() {
		verify(newUser).setId(ID);
		verify(newUser).setName(NAME);
		verify(newUser).setEmail(EMAIL);
	}

	@Override
	protected void assertCloneElement(Object clonnedElement) {
		assertEquals(newUser, clonnedElement);
	}
}
