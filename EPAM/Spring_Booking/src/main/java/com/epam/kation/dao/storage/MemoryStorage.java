package com.epam.kation.dao.storage;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.epam.kation.dao.storage.clonner.Clonner;
import com.epam.kation.dao.storage.comparator.Comparator;
import com.epam.kation.dao.storage.id.generator.IdGenerator;
import com.epam.kation.dao.storage.id.handler.IdHandler;

public class MemoryStorage implements Storage, BeanPostProcessor {

	private final static Logger LOG = Logger.getLogger(MemoryStorage.class);

	private static final String KEY_SEPARATOR = ":";

	private String path;

	private Map<Class<? extends Object>, String> types;

	private Map<String, IdGenerator> idGenerators;
	private Map<String, IdHandler> idHandlers;
	private Map<String, Comparator> comparators;
	private Map<String, Clonner> clonners;

	private Map<String, Object> map;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		LOG.info(path);

		return bean;
	}

	@Override
	public synchronized <T> T create(T element) {
		String type = getType(element);

		IdGenerator idGenerator = getIdGenerator(type);
		IdHandler idHandler = getIdHandler(type);
		Clonner clonner = getClonner(type);

		Long generatedId = idGenerator.genarate(element.getClass());

		idHandler.setId(element, generatedId);

		map.put(getKey(type, generatedId), clonner.clone(element));

		return element;
	}

	@Override
	public synchronized <T> T update(T element) {
		String type = getType(element);

		IdHandler idHandler = getIdHandler(type);
		Clonner clonner = getClonner(type);

		Long id = idHandler.getId(element).get();

		map.put(getKey(type, id), clonner.clone(element));

		return element;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getByExample(T example) {
		String type = getType(example);

		String keyPrefix = getKeyPrefix(type);
		Comparator comparator = getComparator(type);
		Clonner clonner = getClonner(type);

		List<Object> values = getMapStream().filter(byKeyType(keyPrefix)).filter(byExample(example, comparator))
				.map(Map.Entry::getValue).map(clonner::clone).collect(toList());

		return (List<T>) values;
	}

	@SuppressWarnings("unchecked")
	@Override
	public synchronized <T> List<T> getByExample(T example, Integer offset, Integer limit) {
		String type = getType(example);

		String keyPrefix = getKeyPrefix(type);
		Comparator comparator = getComparator(type);
		Clonner clonner = getClonner(type);

		List<Object> values = getMapStream().filter(byKeyType(keyPrefix)).filter(byExample(example, comparator)).skip(offset)
				.limit(limit).map(Map.Entry::getValue).map(clonner::clone).collect(toList());

		return (List<T>) values;
	}

	@Override
	public synchronized <T> boolean removeByExample(T example) {
		String type = getType(example);

		String keyPrefix = getKeyPrefix(type);
		Comparator comparator = getComparator(type);

		List<String> keys = getMapStream().filter(byKeyType(keyPrefix)).filter(byExample(example, comparator))
				.map(Map.Entry::getKey).collect(toList());

		return removeElements(keys);
	}

	private Stream<Entry<String, Object>> getMapStream() {
		return map.entrySet().stream();
	}

	private boolean removeElements(List<String> result) {
		result.stream().forEach(map::remove);

		return isNotEmpty(result);
	}

	private boolean isNotEmpty(List<String> result) {
		return !result.isEmpty();
	}

	private Comparator getComparator(String type) {
		return comparators.get(type);
	}

	private Predicate<? super Entry<String, Object>> byKeyType(String keyPrefix) {
		return entry -> entry.getKey().startsWith(keyPrefix);
	}

	private <T> Predicate<? super Entry<String, Object>> byExample(T example, Comparator comparator) {
		return entry -> comparator.compare(entry.getValue(), example);
	}

	private String getKey(String type, Long id) {
		return getKeyPrefix(type) + id;
	}

	private String getKeyPrefix(String type) {
		return type + KEY_SEPARATOR;
	}

	private IdGenerator getIdGenerator(String type) {
		return idGenerators.get(type);
	}

	private IdHandler getIdHandler(String type) {
		return idHandlers.get(type);
	}

	private <T> String getType(T example) {
		Class<? extends Object> exampleType = example.getClass();

		for (Map.Entry<Class<? extends Object>, String> entry : types.entrySet()) {
			Class<? extends Object> clazz = entry.getKey();

			if (clazz.isAssignableFrom(example.getClass()))
				return entry.getValue();
		}

		LOG.warn("No such type found: " + exampleType.getName());
		throw new UnsupportedOperationException();
	}

	private Clonner getClonner(String type) {
		return clonners.get(type);
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setTypes(Map<Class<? extends Object>, String> types) {
		this.types = types;
	}

	public void setIdGenerators(Map<String, IdGenerator> idGenerators) {
		this.idGenerators = idGenerators;
	}

	public void setIdHandlers(Map<String, IdHandler> idHandlers) {
		this.idHandlers = idHandlers;
	}

	public void setComparators(Map<String, Comparator> comparators) {
		this.comparators = comparators;
	}

	public void setClonners(Map<String, Clonner> clonners) {
		this.clonners = clonners;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
