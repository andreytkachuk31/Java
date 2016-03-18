package com.epam.kation.dao.storage.comparator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public abstract class AbstractComparatorTest {

	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowExeptionWhenCompareObjectsWithIncorrectFirstType() {
		getComparator().compare(new Object(), getElement1());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowExeptionWhenCompareObjectsWithIncorrectSecondType() {
		getComparator().compare(getElement1(), new Object());
	}

	protected void assertCompareEquals() {
		assertTrue(getComparator().compare(getElement1(), getElement2()));
	}

	protected void assertCompareNotEquals() {
		assertFalse(getComparator().compare(getElement1(), getElement2()));
	}

	protected abstract Comparator getComparator();

	protected abstract Object getElement1();

	protected abstract Object getElement2();
}
