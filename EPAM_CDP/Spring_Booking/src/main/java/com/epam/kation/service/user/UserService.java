package com.epam.kation.service.user;

import java.util.List;
import java.util.Optional;

import com.epam.kation.model.user.User;

public interface UserService {

	User create(User user);

	Optional<User> get(Long id);

	Optional<User> getUserByEmail(String email);

	List<User> getUsersByName(String name, Integer offset, Integer limit);

	User update(User user);

	boolean remove(Long id);
}
