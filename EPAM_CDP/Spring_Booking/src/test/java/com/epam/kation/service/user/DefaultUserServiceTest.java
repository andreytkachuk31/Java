package com.epam.kation.service.user;

import static com.google.common.collect.Iterables.getOnlyElement;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.kation.dao.user.UserDao;
import com.epam.kation.model.user.User;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserServiceTest {

	private static final Long ID = 1L;
	private static final int OFFSET = 2;
	private static final int LIMIT = 3;
	private static final Boolean REMOVE_RESULT = Boolean.TRUE;
	private static final String EMAIL = "email";
	private static final String NAME = "name";

	@Mock
	private UserDao userDao;

	@Mock
	private User user;

	@Mock
	private User resultUser;

	@Mock
	private User createdUser;

	@Mock
	private User updatedUser;

	@Mock
	private User newUser;

	@InjectMocks
	private UserService service = new DefaultUserService();

	@Test
	public void shouldCallCreateOnUserDaoWhenCreateUser() {
		when(userDao.create(user)).thenReturn(createdUser);

		User actualCreateUser = service.create(user);

		verify(userDao).create(user);

		assertEquals(createdUser, actualCreateUser);
	}

	@Test
	public void shouldCallUpdateOnUserDaoWhenUpdateUser() {
		when(userDao.update(user)).thenReturn(updatedUser);

		User actualUpdatedUser = service.update(user);

		verify(userDao).update(user);

		assertEquals(updatedUser, actualUpdatedUser);
	}

	@Test
	public void shouldReturnOptionalWhenGetById() {
		when(userDao.get(ID)).thenReturn(of(user));

		Optional<User> actualResult = service.get(ID);

		verify(userDao).get(ID);

		assertEquals(user, actualResult.get());
	}

	@Test
	public void shouldReturnResultListWhenGetUserByEmail() {
		when(userDao.getUserByEmail(EMAIL)).thenReturn(of(resultUser));

		Optional<User> actualResult = service.getUserByEmail(EMAIL);

		verify(userDao).getUserByEmail(EMAIL);

		assertEquals(resultUser, actualResult.get());
	}

	@Test
	public void shouldReturnResultListWhenGetUserByName() {
		when(userDao.getUsersByName(NAME, OFFSET, LIMIT)).thenReturn(newArrayList(resultUser));

		List<User> actualResult = service.getUsersByName(NAME, OFFSET, LIMIT);

		verify(userDao).getUsersByName(NAME, OFFSET, LIMIT);

		assertEquals(resultUser, getOnlyElement(actualResult));
	}

	@Test
	public void shouldRemoveWhenRemove() {
		when(userDao.remove(ID)).thenReturn(REMOVE_RESULT);

		boolean actualResult = service.remove(ID);

		verify(userDao).remove(ID);

		assertEquals(REMOVE_RESULT, actualResult);
	}
}
