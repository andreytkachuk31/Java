package com.epam.kation.dao.storage.id.handler;

import static java.util.Objects.isNull;
import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.util.Optional;

public abstract class AbstractIdHandler implements IdHandler {

	@Override
	public Optional<Long> getId(Object element) {
		validateType(element);

		Long id = getIdProtected(element);

		if (isNull(id))
			return empty();

		return of(id);
	}

	@Override
	public Object setId(Object element, Long id) {
		validateType(element);

		setIdProtected(element, id);

		return element;
	}

	private void validateType(Object element) {
		if (isIncorrectType(element))
			throw new UnsupportedOperationException();
	}

	protected boolean isIncorrectType(Object element) {
		return !isCorrectType(element);
	}

	protected abstract boolean isCorrectType(Object element);

	protected abstract Long getIdProtected(Object element);

	protected abstract void setIdProtected(Object element, Long id);
}
