package com.epam.kation.dao.storage;

import java.util.List;

public interface Storage {

	<T> T create(T element);

	<T> T update(T element);

	<T> List<T> getByExample(T example);

	<T> List<T> getByExample(T example, Integer offset, Integer limit);

	<T> boolean removeByExample(T example);
}
