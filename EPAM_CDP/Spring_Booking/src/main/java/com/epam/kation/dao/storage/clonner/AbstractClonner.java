package com.epam.kation.dao.storage.clonner;

public abstract class AbstractClonner implements Clonner {

	@Override
	public Object clone(Object element) {
		if (isIncorrectType(element))
			throw new UnsupportedOperationException();

		Object clonnerElement = newInstance();

		populate(element, clonnerElement);

		return clonnerElement;
	}

	private boolean isIncorrectType(Object element) {
		return !isCorrectType(element);
	}

	protected abstract boolean isCorrectType(Object element);

	protected abstract Object newInstance();

	protected abstract void populate(Object element, Object newElement);
}
