package com.epam.core.unit.file.index;

import com.epam.core.exception.NoSuchIndexException;
import com.epam.core.unit.file.position.Position;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultIndexTest {

    private static final String KEY = "key";

    private static final long OFFSET = 10;
    private static final int SIZE = 8;

    private Position positionMock;

    @Mock
    private Map<String, Position> indexMapMock;

    @InjectMocks
    private DefaultIndex defaultIndex = new DefaultIndex();

    @Before
    public void before() {
        positionMock = createPositionMock();
    }

    @Test
    public void shouldAddKeyWithPositionWhenCallAdd() {

        defaultIndex.add(KEY, positionMock);

        verify(indexMapMock).put(KEY, positionMock);

    }

    @Test
    public void shouldGetPositionByKeyWhenCallGet() {

        when(indexMapMock.containsKey(KEY)).thenReturn(true);
        when(indexMapMock.get(KEY)).thenReturn(positionMock);

        Position positionResult = defaultIndex.get(KEY);

        verify(indexMapMock).get(KEY);
        assertEquals(positionResult, positionMock);
    }

    @Test(expected = NoSuchIndexException.class)
    public void shouldThrowNoSuchIndexExceptionWhenCallGetWithNotExistingKey() {

        when(indexMapMock.containsKey(KEY)).thenReturn(false);

        defaultIndex.get(KEY);
    }

    @Test
    public void shouldRemoveKeyWithPositionWhenCallRemove() {

        when(indexMapMock.containsKey(KEY)).thenReturn(true);
        when(indexMapMock.remove(KEY)).thenReturn(positionMock);

        Position positionResult = defaultIndex.remove(KEY);

        verify(indexMapMock).remove(KEY);
        assertEquals(positionResult, positionMock);
    }

    @Test(expected = NoSuchIndexException.class)
    public void shouldThrowNoSuchIndexExceptionWhenCallRemoveWithNotExistingKey() {

        when(indexMapMock.containsKey(KEY)).thenReturn(false);

        defaultIndex.remove(KEY);
    }

    private Position createPositionMock() {
        Position positionMock = mock(Position.class);

        when(positionMock.getOffset()).thenReturn(OFFSET);
        when(positionMock.getSize()).thenReturn(SIZE);

        return positionMock;
    }
}