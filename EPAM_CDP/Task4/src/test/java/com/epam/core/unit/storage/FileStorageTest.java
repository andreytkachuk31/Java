package com.epam.core.unit.storage;

import com.epam.core.unit.file.index.Index;
import com.epam.core.unit.file.position.Position;
import com.epam.core.unit.file.reader.FileProviderReader;
import com.epam.core.unit.file.writer.FileProviderWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileStorageTest {

    private static final String KEY = "key";
    private static final String VALUE = "value";
    private static final String UPDATE_VALUE = "updateDate";

    private static final long OFFSET = 10;
    private static final int SIZE = 10;

    @Mock
    private FileProviderReader fileProviderReaderMock;

    @Mock
    private FileProviderWriter fileProviderWriterMock;

    @Mock
    private Index indexMock;

    @InjectMocks
    private FileStorage fileStorage = new FileStorage();

    @Test
    public void shouldCreateKeyValueWhenCallCreate() {
        Position positionMock = createPositionMock();
        mockFileWriter(positionMock, VALUE);

        fileStorage.create(KEY, VALUE);

        verify(fileProviderWriterMock).append(KEY, VALUE);
        verify(indexMock).add(KEY, positionMock);
    }

    @Test
    public void shouldReadValueWithKeyWhenCallRead() {
        Position positionMock = createPositionMock();
        mockIndex(positionMock);
        mockFileReader(positionMock);

        String value = fileStorage.read(KEY);

        assertEquals(VALUE, value);
    }

    @Test
    public void shouldUpdateKeyValueWhenCallUpdate() {
        Position positionMock = createPositionMock();
        mockIndex(positionMock);
        mockFileWriter(positionMock, UPDATE_VALUE);
        mockFileReader(positionMock);

        String value = fileStorage.update(KEY, UPDATE_VALUE);

        verify(fileProviderWriterMock).append(KEY, UPDATE_VALUE);
        verify(indexMock).add(KEY, positionMock);
        verify(fileProviderWriterMock).remove(positionMock);
        assertEquals(VALUE, value);
    }

    @Test
    public void shouldRemoveValueByKeyWhenCallRemove() {
        Position positionMock = createPositionMock();
        mockIndex(positionMock);
        mockFileReader(positionMock);

        String value = fileStorage.remove(KEY);

        verify(fileProviderWriterMock).remove(positionMock);
        assertEquals(value, VALUE);
    }

    private void mockFileWriter(Position positionMock, String value) {
        when(fileProviderWriterMock.append(KEY, value)).thenReturn(positionMock);
    }

    private void mockFileReader(Position positionMock) {
        when(fileProviderReaderMock.read(positionMock)).thenReturn(VALUE);
    }

    private void mockIndex(Position positionMock) {
        when(indexMock.get(KEY)).thenReturn(positionMock);
        when(indexMock.remove(KEY)).thenReturn(positionMock);
    }

    private Position createPositionMock() {
        Position positionMock = mock(Position.class);

        when(positionMock.getOffset()).thenReturn(OFFSET);
        when(positionMock.getSize()).thenReturn(SIZE);

        return positionMock;
    }
}