package com.epam.kation.dao.storage.id.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

public abstract class AbstractHandlerTest {

	protected static final Long ID = 1L;

	@Test
	public void shouldReturnObjectIdWhenGetIdWithObject() {
		setUpId(ID);

		Optional<Long> actualId = getIdHandler().getId(getType());

		assertTrue(actualId.isPresent());
		assertEquals(ID, actualId.get());
	}

	@Test
	public void shouldReturnEmptyIdWhenGetIdWithoutObjectId() {
		setUpId(null);

		Optional<Long> actualId = getIdHandler().getId(getType());

		assertFalse(actualId.isPresent());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowExeptionWhenGetIdWithNotObjectType() {
		getIdHandler().getId(new Object());
	}

	@Test
	public void shouldSetObjectIdWhenSetIdWithObjectAndId() {
		Object actualObject = getIdHandler().setId(getType(), ID);

		verifySetId(ID);
		assertEquals(getType(), actualObject);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowExeptionWhenSetIdWithNotObjectType() {
		getIdHandler().setId(new Object(), ID);
	}

	protected abstract IdHandler getIdHandler();

	protected abstract Object getType();

	protected abstract void setUpId(Long id);

	protected abstract void verifySetId(Long id);
}
