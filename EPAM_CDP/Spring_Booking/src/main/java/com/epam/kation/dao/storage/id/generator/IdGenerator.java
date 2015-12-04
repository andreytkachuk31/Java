package com.epam.kation.dao.storage.id.generator;

public interface IdGenerator {

	Long genarate(Class<? extends Object> type);
}
