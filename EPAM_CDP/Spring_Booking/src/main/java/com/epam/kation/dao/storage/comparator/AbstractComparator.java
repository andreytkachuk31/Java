package com.epam.kation.dao.storage.comparator;

import static java.util.Objects.isNull;

import java.util.function.BiPredicate;

public abstract class AbstractComparator implements Comparator {

	@Override
	public boolean compare(Object element1, Object element2) {
		if (isIncorrectType(element1) || isIncorrectType(element2))
			throw new UnsupportedOperationException();

		return compareAfterValidation(element1, element2);
	}

	private boolean isIncorrectType(Object element) {
		return !isCorrectType(element);
	}

	protected <T> boolean compareWithNulls(T element1, T element2, BiPredicate<T, T> predicat) {
		if (isNull(element1))
			return isNull(element2);
		else
			return predicat.test(element1, element2);
	}

	protected abstract boolean isCorrectType(Object element);

	protected abstract boolean compareAfterValidation(Object element1, Object element2);
}
