package com.epam.kation.dao.storage.id.generator;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;

public abstract class AbstractIdGeneratorTest {

	private static final int GENERATE_LOOP_NUMBER = 10;

	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowExeptionWhenGenerateeWithNotEventType() {
		getIdGenerator().genarate(Object.class);
	}

	@Test
	public void shouldGenerateDifferentIds() {
		List<Long> ids = newArrayList();

		for (int i = 0; i < GENERATE_LOOP_NUMBER; i++) {
			Long id = getIdGenerator().genarate(getType());

			assertFalse(ids.contains(id));
			ids.add(id);
		}
	}

	protected abstract Class<? extends Object> getType();

	protected abstract IdGenerator getIdGenerator();
}
