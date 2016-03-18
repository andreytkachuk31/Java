package com.epam.kation.service.user;

import java.util.List;
import java.util.Optional;

import com.epam.kation.dao.user.UserDao;
import com.epam.kation.model.user.User;

public class DefaultUserService implements UserService {

	private UserDao userDao;

	@Override
	public User create(User user) {
		return userDao.create(user);
	}

	@Override
	public Optional<User> get(Long id) {
		return userDao.get(id);
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public List<User> getUsersByName(String name, Integer offset, Integer limit) {
		return userDao.getUsersByName(name, offset, limit);
	}

	@Override
	public User update(User user) {
		return userDao.update(user);
	}

	@Override
	public boolean remove(Long id) {
		return userDao.remove(id);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
