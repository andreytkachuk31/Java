package com.epam.kation.dao.user;

import static com.google.common.collect.Iterables.getOnlyElement;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.kation.dao.storage.Storage;
import com.epam.kation.model.user.User;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserDaoTest {

	private static final Long ID = 1L;
	private static final int OFFSET = 2;
	private static final int LIMIT = 2;
	private static final boolean REMOVE_RESULT = true;
	private static final String EMAIL = "title";
	private static final String NAME = "name";

	@Mock
	private Storage storage;

	@Mock
	private User user;

	@Mock
	private User updatedUser;

	@Mock
	private User newUser;

	@InjectMocks
	private UserDao dao = new DefaultUserDao() {

		@Override
		public User newUser() {
			return newUser;
		}
	};

	@Test
	public void shouldCallCreateOnStorageWhenCreate() {
		when(storage.create(user)).thenReturn(updatedUser);

		User actualUpdatedUser = dao.create(user);

		verify(storage).create(user);

		assertEquals(updatedUser, actualUpdatedUser);
	}

	@Test
	public void shouldCallUpdateOnStorageWhenUpdate() {
		when(storage.update(user)).thenReturn(updatedUser);

		User actualUpdatedUser = dao.update(user);

		verify(storage).update(user);

		assertEquals(updatedUser, actualUpdatedUser);
	}

	@Test
	public void shouldReturnEmptyWhenGetByIdWithUnexistingElement() {
		InOrder order = inOrder(newUser, storage);
		when(storage.getByExample(newUser)).thenReturn(emptyList());

		Optional<User> actualUser = dao.get(ID);

		order.verify(newUser).setId(ID);
		order.verify(storage).getByExample(newUser);

		assertFalse(actualUser.isPresent());
	}

	@Test
	public void shouldReturnElementWhenGetByIdWithExistingElement() {
		InOrder order = inOrder(newUser, storage);
		when(storage.getByExample(newUser)).thenReturn(newArrayList(user));

		Optional<User> actualUser = dao.get(ID);

		order.verify(newUser).setId(ID);
		order.verify(storage).getByExample(newUser);

		assertEquals(user, actualUser.get());
	}

	@Test
	public void shouldReturnEmptyWhenGetByEmailWithUnexistingElement() {
		InOrder order = inOrder(newUser, storage);
		when(storage.getByExample(newUser)).thenReturn(emptyList());

		Optional<User> actualUser = dao.getUserByEmail(EMAIL);

		order.verify(newUser).setEmail(EMAIL);
		order.verify(storage).getByExample(newUser);

		assertFalse(actualUser.isPresent());
	}

	@Test
	public void shouldReturnElementWhenGetByEmailWithExistingElement() {
		InOrder order = inOrder(newUser, storage);
		when(storage.getByExample(newUser)).thenReturn(newArrayList(user));

		Optional<User> actualUser = dao.getUserByEmail(EMAIL);

		order.verify(newUser).setEmail(EMAIL);
		order.verify(storage).getByExample(newUser);

		assertEquals(user, actualUser.get());
	}

	@Test
	public void shouldCallGetByExampleOnStorageWhenGetForDay() {
		InOrder order = inOrder(newUser, storage);
		when(storage.getByExample(newUser, OFFSET, LIMIT)).thenReturn(newArrayList(user));

		List<User> actualUsers = dao.getUsersByName(NAME, OFFSET, LIMIT);

		order.verify(newUser).setName(NAME);
		order.verify(storage).getByExample(newUser, OFFSET, LIMIT);

		assertEquals(user, getOnlyElement(actualUsers));
	}

	@Test
	public void shouldCallRemoveByExampleOnStorageWhenRemove() {
		InOrder order = inOrder(newUser, storage);
		when(storage.removeByExample(newUser)).thenReturn(REMOVE_RESULT);

		boolean actualResult = dao.remove(ID);

		order.verify(newUser).setId(ID);
		order.verify(storage).removeByExample(newUser);

		assertEquals(REMOVE_RESULT, actualResult);
	}
}
