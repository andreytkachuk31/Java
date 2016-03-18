package com.epam.kation.dao.storage.id.handler;

import java.util.Optional;

public interface IdHandler {

	Optional<Long> getId(Object element);

	Object setId(Object element, Long id);
}
