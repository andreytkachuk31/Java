package com.epam.kation.dao.storage.comparator;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import com.epam.kation.model.user.User;

public class UserComparator extends AbstractComparator {

	@Override
	protected boolean isCorrectType(Object element) {
		return element instanceof User;
	}

	@Override
	protected boolean compareAfterValidation(Object element1, Object element2) {
		User user1 = (User) element1;
		User user2 = (User) element2;

		boolean compareId = compareId(user1, user2);
		boolean compareName = compareName(user1, user2);
		boolean compareEmail = compareEmail(user1, user2);

		return compareId && compareName && compareEmail;
	}

	private boolean compareEmail(User user1, User user2) {
		String date1 = user1.getEmail();
		String date2 = defaultIfNull(user2.getEmail(), date1);

		return compareWithNulls(date1, date2, String::equals);
	}

	private boolean compareName(User user1, User user2) {
		String title1 = user1.getName();
		String title2 = defaultIfNull(user2.getName(), title1);

		return compareWithNulls(title1, title2, String::contains);
	}

	private boolean compareId(User user1, User user2) {
		Long id1 = user1.getId();
		Long id2 = defaultIfNull(user2.getId(), id1);

		return compareWithNulls(id1, id2, Long::equals);
	}
}
