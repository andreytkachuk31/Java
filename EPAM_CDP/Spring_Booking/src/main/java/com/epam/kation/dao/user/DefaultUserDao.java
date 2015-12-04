package com.epam.kation.dao.user;

import static com.google.common.collect.Iterables.getOnlyElement;
import static java.util.Optional.empty;

import java.util.List;
import java.util.Optional;

import com.epam.kation.dao.storage.Storage;
import com.epam.kation.model.user.User;

public abstract class DefaultUserDao implements UserDao {

	private Storage storage;

	@Override
	public User create(User user) {
		return storage.create(user);
	}

	@Override
	public User update(User user) {
		return storage.update(user);
	}

	@Override
	public Optional<User> get(Long id) {
		User user = newUser();

		user.setId(id);

		List<User> users = storage.getByExample(user);

		if (users.isEmpty())
			return empty();

		return Optional.of(getOnlyElement(users));
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		User user = newUser();

		user.setEmail(email);

		List<User> users = storage.getByExample(user);

		if (users.isEmpty())
			return empty();

		return Optional.of(getOnlyElement(users));
	}

	@Override
	public List<User> getUsersByName(String name, Integer offset, Integer limit) {
		User user = newUser();

		user.setName(name);

		return storage.getByExample(user, offset, limit);
	}

	@Override
	public boolean remove(Long id) {
		User user = newUser();

		user.setId(id);

		return storage.removeByExample(user);
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public abstract User newUser();
}
