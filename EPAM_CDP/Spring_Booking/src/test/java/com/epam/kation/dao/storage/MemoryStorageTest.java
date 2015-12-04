package com.epam.kation.dao.storage;

import static com.google.common.collect.Iterables.getOnlyElement;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Maps.newLinkedHashMap;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.kation.dao.storage.clonner.Clonner;
import com.epam.kation.dao.storage.comparator.Comparator;
import com.epam.kation.dao.storage.id.generator.IdGenerator;
import com.epam.kation.dao.storage.id.handler.IdHandler;

@RunWith(MockitoJUnitRunner.class)
public class MemoryStorageTest {

	private static final Class<TypeOne> TYPE_ONE_CLASS = TypeOne.class;
	private static final Class<TypeTwo> TYPE_TWO_CLASS = TypeTwo.class;

	private static final String TYPE_ONE_NAME = TYPE_ONE_CLASS.getName();
	private static final String TYPE_TWO_NAME = TYPE_TWO_CLASS.getName();

	private static final String KEY_SEPARATOR = ":";

	private static final int ZERO_OFFSET = 0;
	private static final int MAX_LIMIT = Integer.MAX_VALUE;

	private static final long ZERO_ID = 0L;
	private static final long ONE_ID = 1L;
	private static final long TWO_ID = 2L;

	private static final int LIMIT_ONE = 1;
	private static final int OFFSET_ONE = 1;

	@Mock
	private IdGenerator typeOneIdGenerator;

	@Mock
	private IdGenerator typeTwoIdGenerator;

	@Mock
	private IdHandler typeOneIdHandler;

	@Mock
	private IdHandler typeTwoIdHandler;

	@Mock
	private Comparator typeOneComparator;

	@Mock
	private Comparator typeTwoComparator;

	@Mock
	private Clonner typeOneClonner;

	@Mock
	private Clonner typeTwoClonner;

	private Map<String, Object> map = newLinkedHashMap();

	@InjectMocks
	private MemoryStorage storage = new MemoryStorage();

	@Before
	public void setUp() {
		setUpTypes();

		setUpIdGenerators();
		setUpIdHandlers();
		setUpTypeComparators();
		setUpClonners();

		setUpMap();
	}

	@Test
	public void shouldCreateNewTypeOneObjectWhenCreateWithNewTypeOneObject() {
		// given
		TypeOne typeOne = newTypeOne();
		TypeOne safeTypeOne = newTypeOne();

		setUpTypeOneIdHandler(typeOne, empty());
		setUpTypeOneIdGenerator(TYPE_ONE_CLASS, ZERO_ID);
		setUpTypeOneClonner(typeOne, safeTypeOne);

		InOrder order = inOrder(typeOneIdHandler, typeOneClonner);

		// when
		TypeOne actualtypeOne = storage.create(typeOne);

		// then
		verify(typeOneIdGenerator).genarate(TYPE_ONE_CLASS);
		order.verify(typeOneIdHandler).setId(typeOne, ZERO_ID);
		order.verify(typeOneClonner).clone(typeOne);

		assertMapSize(1);
		assertMapContains(TYPE_ONE_NAME, ZERO_ID, safeTypeOne);

		assertEquals(typeOne, actualtypeOne);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowExeptionWhenCreateWithUnknowTypeObject() {
		// given
		TypeThree typeThree = newTypeThree();

		// when
		storage.create(typeThree);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowExeptionWhenUpdateWithUnknowTypeObject() {
		// given
		TypeThree typeThree = newTypeThree();

		// when
		storage.update(typeThree);
	}

	@Test
	public void shouldNotThrowExeptionWhenCreateWithExtendedType() {
		// given
		TypeFour typeFour = new TypeFour();

		// when
		storage.create(typeFour);
	}

	@Test
	public void shouldUpdateOldTypeOneObjectWhenUpdateWithNewTypeOneObject() {
		// given
		TypeOne typeOne1 = newTypeOne();
		TypeOne typeOne2 = newTypeOne();
		TypeOne safeTypeOne2 = newTypeOne();

		mapPut(TYPE_ONE_NAME, ZERO_ID, typeOne1);

		setUpTypeOneIdHandler(typeOne2, of(ZERO_ID));
		setUpTypeOneClonner(typeOne2, safeTypeOne2);

		InOrder order = inOrder(typeOneIdHandler, typeOneClonner);

		// when
		TypeOne actualTypeOne2 = storage.update(typeOne2);

		// then
		order.verify(typeOneIdHandler).getId(typeOne2);
		verify(typeOneIdGenerator, never()).genarate(TYPE_ONE_CLASS);
		order.verify(typeOneIdHandler, never()).setId(typeOne2, ZERO_ID);
		order.verify(typeOneClonner).clone(typeOne2);

		assertMapSize(1);
		assertMapContains(TYPE_ONE_NAME, ZERO_ID, safeTypeOne2);

		assertEquals(typeOne2, actualTypeOne2);
	}

	@Test
	public void shouldCreateNewTypeOneObjectWhenCreateWithNewTypeOneObjectAndAlreadyExistsOldTypeOneObjectWithOtherId() {
		// given
		TypeOne typeOne1 = newTypeOne();
		TypeOne typeOne2 = newTypeOne();
		TypeOne safeTypeOne2 = newTypeOne();

		mapPut(TYPE_ONE_NAME, ZERO_ID, typeOne1);

		setUpTypeOneIdHandler(typeOne2, empty());
		setUpTypeOneIdGenerator(TYPE_ONE_CLASS, ONE_ID);
		setUpTypeOneClonner(typeOne2, safeTypeOne2);

		InOrder order = inOrder(typeOneIdHandler, typeOneClonner);

		// when
		TypeOne actualTypeOne2 = storage.create(typeOne2);

		// then
		verify(typeOneIdGenerator).genarate(TYPE_ONE_CLASS);
		order.verify(typeOneIdHandler).setId(typeOne2, ONE_ID);
		order.verify(typeOneClonner).clone(typeOne2);

		assertMapSize(2);
		assertMapContains(TYPE_ONE_NAME, ZERO_ID, typeOne1);
		assertMapContains(TYPE_ONE_NAME, ONE_ID, safeTypeOne2);

		assertEquals(typeOne2, actualTypeOne2);
	}

	@Test
	public void shouldCreateNewTypeOneObjectWhenCreateWithNewTypeOneObjectAndTypeTwoObjectAlreadyExists() {
		// given
		TypeOne typeOne = newTypeOne();
		TypeOne safeTypeOne = newTypeOne();
		TypeTwo typeTwo = newTypeTwo();

		mapPut(TYPE_TWO_NAME, ZERO_ID, typeTwo);

		setUpTypeOneIdHandler(typeOne, empty());
		setUpTypeOneIdGenerator(TYPE_ONE_CLASS, ZERO_ID);
		setUpTypeOneClonner(typeOne, safeTypeOne);

		InOrder order = inOrder(typeOneIdHandler, typeOneClonner);

		// when
		TypeOne actualTypeOne = storage.create(typeOne);

		// then
		verify(typeOneIdGenerator).genarate(TYPE_ONE_CLASS);
		order.verify(typeOneIdHandler).setId(typeOne, ZERO_ID);
		order.verify(typeOneClonner).clone(typeOne);

		assertMapSize(2);
		assertMapContains(TYPE_ONE_NAME, ZERO_ID, safeTypeOne);
		assertMapContains(TYPE_TWO_NAME, ZERO_ID, typeTwo);

		assertEquals(typeOne, actualTypeOne);
	}

	@Test
	public void shouldReturnEmptyListWhenGetByExampleOnEmptyStorage() {
		assertTrue(storage.getByExample(newTypeOne(), ZERO_OFFSET, MAX_LIMIT).isEmpty());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowExeptionWhenGetByExampleWithUnknowTypeObject() {
		storage.getByExample(newTypeThree(), ZERO_OFFSET, MAX_LIMIT);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowExeptionWhenGetJustByExampleWithUnknowTypeObject() {
		storage.getByExample(newTypeThree());
	}

	@Test
	public void shouldReturnFilteredListByTypeOneObjectWhenGetJustByExampleWithTypeOneObject() {
		// given
		TypeOne typeOne = newTypeOne();
		TypeTwo typeTwo = newTypeTwo();

		TypeOne safeTypeOne = newTypeOne();
		TypeOne typeOneExample = newTypeOne();

		mapPut(TYPE_ONE_NAME, ZERO_ID, typeOne);
		mapPut(TYPE_TWO_NAME, ZERO_ID, typeTwo);

		when(typeOneComparator.compare(typeOne, typeOneExample)).thenReturn(TRUE);
		when(typeOneComparator.compare(typeTwo, typeOneExample)).thenReturn(TRUE);
		setUpTypeOneClonner(typeOne, safeTypeOne);

		// when
		List<TypeOne> results = storage.getByExample(typeOneExample);

		// then
		verify(typeOneClonner).clone(typeOne);

		assertEquals(safeTypeOne, getOnlyElement(results));
	}

	@Test
	public void shouldReturnEmptyListWhenGetJustByExampleWithTypeOneObjectAndNoMatchFound() {
		// given
		TypeOne typeOne1 = newTypeOne();
		TypeOne typeOne2 = newTypeOne();

		TypeOne typeOneExample = newTypeOne();

		mapPut(TYPE_ONE_NAME, ZERO_ID, typeOne1);
		mapPut(TYPE_ONE_NAME, ONE_ID, typeOne2);

		when(typeOneComparator.compare(typeOne1, typeOneExample)).thenReturn(FALSE);
		when(typeOneComparator.compare(typeOne2, typeOneExample)).thenReturn(FALSE);

		// when
		List<TypeOne> result = storage.getByExample(typeOneExample);

		// then
		verify(typeOneClonner, never()).clone(any());

		assertTrue(result.isEmpty());
	}

	@Test
	public void shouldReturnFilteredListByTypeOneObjectWhenGetByExampleWithTypeOneObject() {
		// given
		TypeOne typeOne = newTypeOne();
		TypeTwo typeTwo = newTypeTwo();

		TypeOne safeTypeOne = newTypeOne();
		TypeOne typeOneExample = newTypeOne();

		mapPut(TYPE_ONE_NAME, ZERO_ID, typeOne);
		mapPut(TYPE_TWO_NAME, ZERO_ID, typeTwo);

		when(typeOneComparator.compare(typeOne, typeOneExample)).thenReturn(TRUE);
		when(typeOneComparator.compare(typeTwo, typeOneExample)).thenReturn(TRUE);
		setUpTypeOneClonner(typeOne, safeTypeOne);

		// when
		List<TypeOne> results = storage.getByExample(typeOneExample, ZERO_OFFSET, MAX_LIMIT);

		// then
		verify(typeOneClonner).clone(typeOne);

		assertEquals(safeTypeOne, getOnlyElement(results));
	}

	@Test
	public void shouldReturnEmptyListWhenGetByExampleWithTypeOneObjectAndNoMatchFound() {
		// given
		TypeOne typeOne1 = newTypeOne();
		TypeOne typeOne2 = newTypeOne();

		TypeOne typeOneExample = newTypeOne();

		mapPut(TYPE_ONE_NAME, ZERO_ID, typeOne1);
		mapPut(TYPE_ONE_NAME, ONE_ID, typeOne2);

		when(typeOneComparator.compare(typeOne1, typeOneExample)).thenReturn(FALSE);
		when(typeOneComparator.compare(typeOne2, typeOneExample)).thenReturn(FALSE);

		// when
		List<TypeOne> result = storage.getByExample(typeOneExample, ZERO_OFFSET, MAX_LIMIT);

		// then
		verify(typeOneClonner, never()).clone(any());

		assertTrue(result.isEmpty());
	}

	@Test
	public void shouldReturnFilteredListByTypeOneObjectWithOffsetAndLimitWhenGetByExampleWithTypeOneObjectAndOffsetAndLimit() {
		// given
		TypeOne typeOne1 = newTypeOne();
		TypeTwo typeTwo = newTypeTwo();
		TypeOne typeOne2 = newTypeOne();
		TypeOne typeOne3 = newTypeOne();

		TypeOne safeTypeOne2 = newTypeOne();
		TypeOne typeOneExample = newTypeOne();

		mapPut(TYPE_ONE_NAME, ZERO_ID, typeOne1);
		mapPut(TYPE_TWO_NAME, ZERO_ID, typeTwo);
		mapPut(TYPE_ONE_NAME, ONE_ID, typeOne2);
		mapPut(TYPE_ONE_NAME, TWO_ID, typeOne3);

		when(typeOneComparator.compare(typeOne1, typeOneExample)).thenReturn(TRUE);
		when(typeOneComparator.compare(typeTwo, typeOneExample)).thenReturn(TRUE);
		when(typeOneComparator.compare(typeOne2, typeOneExample)).thenReturn(TRUE);
		when(typeOneComparator.compare(typeOne3, typeOneExample)).thenReturn(TRUE);
		setUpTypeOneClonner(typeOne2, safeTypeOne2);

		// when
		List<TypeOne> results = storage.getByExample(typeOneExample, OFFSET_ONE, LIMIT_ONE);

		// then
		verify(typeOneClonner).clone(typeOne2);

		assertEquals(safeTypeOne2, getOnlyElement(results));
	}

	@Test
	public void shouldRemoveTypeOneObjectWhenRemoveWithTypeOneObject() {
		// given
		TypeOne typeOne1 = newTypeOne();
		TypeOne typeOne2 = newTypeOne();

		TypeOne typeOneExample = newTypeOne();

		mapPut(TYPE_ONE_NAME, ZERO_ID, typeOne1);
		mapPut(TYPE_ONE_NAME, ONE_ID, typeOne2);

		when(typeOneComparator.compare(typeOne1, typeOneExample)).thenReturn(TRUE);
		when(typeOneComparator.compare(typeOne2, typeOneExample)).thenReturn(FALSE);

		// when
		boolean result = storage.removeByExample(typeOneExample);

		// then
		assertTrue(result);

		assertMapSize(1);
		assertMapContains(TYPE_ONE_NAME, ONE_ID, typeOne2);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowExeptionWhenRemoveWithUnknowTypeObject() {
		storage.removeByExample(newTypeThree());
	}

	@Test
	public void shouldNotRemoveTypeOneObjectWhenRemoveWithTypeOneObjectAndNoMatchFound() {
		// given
		TypeOne typeOne1 = newTypeOne();
		TypeOne typeOne2 = newTypeOne();

		TypeOne typeOneExample = newTypeOne();

		mapPut(TYPE_ONE_NAME, ZERO_ID, typeOne1);
		mapPut(TYPE_ONE_NAME, ONE_ID, typeOne2);

		when(typeOneComparator.compare(typeOne1, typeOneExample)).thenReturn(FALSE);
		when(typeOneComparator.compare(typeOne2, typeOneExample)).thenReturn(FALSE);

		// when
		boolean result = storage.removeByExample(typeOneExample);

		// then
		assertFalse(result);

		assertMapSize(2);
		assertMapContains(TYPE_ONE_NAME, ZERO_ID, typeOne1);
		assertMapContains(TYPE_ONE_NAME, ONE_ID, typeOne2);
	}

	private void setUpTypes() {
		Map<Class<? extends Object>, String> types = newHashMap();

		types.put(TYPE_TWO_CLASS, TYPE_TWO_NAME);
		types.put(TYPE_ONE_CLASS, TYPE_ONE_NAME);

		storage.setTypes(types);
	}

	private void setUpIdGenerators() {
		Map<String, IdGenerator> idGenerators = newHashMap();

		idGenerators.put(TYPE_ONE_NAME, typeOneIdGenerator);
		idGenerators.put(TYPE_TWO_NAME, typeTwoIdGenerator);

		storage.setIdGenerators(idGenerators);
	}

	private void setUpIdHandlers() {
		Map<String, IdHandler> idHandlers = newHashMap();

		idHandlers.put(TYPE_ONE_NAME, typeOneIdHandler);
		idHandlers.put(TYPE_TWO_NAME, typeTwoIdHandler);

		storage.setIdHandlers(idHandlers);
	}

	private void setUpTypeComparators() {
		Map<String, Comparator> typeComparators = newHashMap();

		typeComparators.put(TYPE_ONE_NAME, typeOneComparator);
		typeComparators.put(TYPE_TWO_NAME, typeTwoComparator);

		storage.setComparators(typeComparators);
	}

	private void setUpClonners() {
		Map<String, Clonner> clonners = newHashMap();

		clonners.put(TYPE_ONE_NAME, typeOneClonner);
		clonners.put(TYPE_TWO_NAME, typeTwoClonner);

		storage.setClonners(clonners);
	}

	private void setUpMap() {
		storage.setMap(map);
	}

	private TypeOne newTypeOne() {
		return new TypeOne();
	}

	private TypeTwo newTypeTwo() {
		return new TypeTwo();
	}

	private TypeThree newTypeThree() {
		return new TypeThree();
	}

	private void setUpTypeOneClonner(TypeOne element, TypeOne safeElement) {
		when(typeOneClonner.clone(element)).thenReturn(safeElement);
	}

	private void setUpTypeOneIdGenerator(Class<TypeOne> type, long generatedId) {
		when(typeOneIdGenerator.genarate(type)).thenReturn(generatedId);
	}

	private void setUpTypeOneIdHandler(TypeOne element, Optional<Long> id) {
		when(typeOneIdHandler.getId(element)).thenReturn(id);
	}

	private void assertMapSize(int size) {
		assertEquals(size, map.size());
	}

	private void assertMapContains(String typeName, long id, Object element) {
		String key = getKey(typeName, id);

		assertTrue(map.containsKey(key));
		assertEquals(map.get(key), element);
	}

	private void mapPut(String typeName, long id, Object element) {
		String key = getKey(typeName, id);

		map.put(key, element);
	}

	private String getKey(String typeName, long id) {
		return typeName + KEY_SEPARATOR + id;
	}

	private class TypeOne {
	}

	private class TypeTwo {
	}

	private class TypeThree {
	}
	
	private class TypeFour extends TypeOne {
	}
}
