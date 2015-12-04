package com.epam.kation.dao.storage.id.generator;

import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractIdGenerator implements IdGenerator {

	private AtomicLong id = new AtomicLong(0L);

	@Override
	public Long genarate(Class<? extends Object> type) {
		validateType(type);

		return id.getAndIncrement();
	}

	private void validateType(Class<? extends Object> type) {
		if (isCorrectType(type))
			throw new UnsupportedOperationException();
	}

	private boolean isCorrectType(Class<? extends Object> type) {
		return !isIncorrectType(type);
	}

	protected abstract boolean isIncorrectType(Class<? extends Object> type);
}
