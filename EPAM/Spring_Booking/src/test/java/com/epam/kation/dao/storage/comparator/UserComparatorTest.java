package com.epam.kation.dao.storage.comparator;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.kation.model.user.User;

@RunWith(MockitoJUnitRunner.class)
public class UserComparatorTest extends AbstractComparatorTest {

	private static final Long ZERO_ID = 0L;
	private static final Long ONE_ID = 1L;

	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "secondName";

	private static final String FIRST_EMAIL = "firstEmail";
	private static final String LAST_EMAIL = "secondEmail";

	@Mock
	private User user1;

	@Mock
	private User user2;

	private Comparator comparator = new UserComparator();

	@Test
	public void shouldReturnTrueWhenCompareWithTwoNullUsers() {
		setUpUser1(null, null, null);
		setUpUser2(null, null, null);

		assertCompareEquals();
	}

	@Test
	public void shouldReturnTrueWhenCompareWithOneFullAndOneNullObjects() {
		setUpUser1(ZERO_ID, FIRST_NAME, FIRST_EMAIL);
		setUpUser2(null, null, null);

		assertCompareEquals();
	}

	@Test
	public void shoulReturnFalseWhenCompareWithOneNullAndOneFullObjects() {
		setUpUser1(null, null, null);
		setUpUser2(ZERO_ID, FIRST_NAME, FIRST_EMAIL);

		assertCompareNotEquals();
	}

	@Test
	public void shouldReturnFalseWhenCompareWithOneSmallerIdAndOneBIdObjects() {
		setUpUser1(ZERO_ID, null, null);
		setUpUser2(ONE_ID, null, null);

		assertCompareNotEquals();
	}

	@Test
	public void shouldReturnTrueWhenCompareWithOneBiggerIdAndOneBiggerIdObjects() {
		setUpUser1(ONE_ID, null, null);
		setUpUser2(ONE_ID, null, null);

		assertCompareEquals();
	}

	@Test
	public void shouldReturnFalseWhenCompareWithOneBiggerIdAndOneSmallerIdObjects() {
		setUpUser1(ONE_ID, null, null);
		setUpUser2(ZERO_ID, null, null);

		assertCompareNotEquals();
	}

	@Test
	public void shouldReturnFalseWhenCompareWithOneFirstNameAndOneLastNameObjects() {
		setUpUser1(null, FIRST_NAME, null);
		setUpUser2(null, LAST_NAME, null);

		assertCompareNotEquals();
	}

	@Test
	public void shouldReturnTrueWhenCompareWithOneFirstLastFirstNameAndOneLastNameObjects() {
		setUpUser1(null, FIRST_NAME + LAST_NAME + FIRST_NAME, null);
		setUpUser2(null, LAST_NAME, null);

		assertCompareEquals();
	}

	@Test
	public void shouldReturnFalseWhenCompareWithOneFirstEmailAndOneEmailDateObjects() {
		setUpUser1(null, null, FIRST_EMAIL);
		setUpUser2(null, null, LAST_EMAIL);

		assertCompareNotEquals();
	}

	@Test
	public void shouldReturnTrueWhenCompareWithOneFirstEmailAndOneFirstEmailObjects() {
		setUpUser1(null, null, FIRST_EMAIL);
		setUpUser2(null, null, FIRST_EMAIL);

		assertCompareEquals();
	}

	@Test
	public void shouldReturnFalseWhenCompareWithOneLastEmailAndOneFirstEmailObjects() {
		setUpUser1(null, null, LAST_EMAIL);
		setUpUser2(null, null, FIRST_EMAIL);

		assertCompareNotEquals();
	}

	@Override
	protected Comparator getComparator() {
		return comparator;
	}

	@Override
	protected Object getElement1() {
		return user1;
	}

	@Override
	protected Object getElement2() {
		return user2;
	}

	private void setUpUser1(Long id, String userName, String userEmail) {
		setUpUser(user1, id, userName, userEmail);
	}

	private void setUpUser2(Long id, String userName, String userEmail) {
		setUpUser(user2, id, userName, userEmail);
	}

	private void setUpUser(User user, Long id, String userName, String userEmail) {
		when(user.getId()).thenReturn(id);
		when(user.getName()).thenReturn(userName);
		when(user.getEmail()).thenReturn(userEmail);
	}
}
