package com.epam.kation.dao.storage.clonner;

import org.junit.Test;

public abstract class AbstractClonnerTest {

	@Test
	public void shouldCloneWholeEventWhenCloneWithEvent() {
		Object element = getElement();

		Object actualElement = getClonner().clone(element);

		verifyClonned();
		assertCloneElement(actualElement);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowExeptionWhenCloneWithNotEvent() {
		getClonner().clone(new Object());
	}

	protected abstract Object getElement();

	protected abstract Clonner getClonner();

	protected abstract void verifyClonned();

	protected abstract void assertCloneElement(Object clonnedElement);
}
