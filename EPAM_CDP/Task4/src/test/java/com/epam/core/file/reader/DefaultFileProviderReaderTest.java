package com.epam.core.file.reader;

import com.epam.core.exception.FileReadException;
import com.epam.core.file.json.JsonEntry;
import com.epam.core.file.position.Position;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultFileProviderReaderTest {

    private static final String KEY = "key";
    private static final String VALUE = "value";

    private static final long OFFSET = 10;
    private static final int SIZE = 10;

    private Position positionMock;

    private FileChannel fileChannelMock;

    @Spy
    private DefaultFileProviderReader fileProviderReader = new DefaultFileProviderReader() {

        @Override
        protected JsonEntry convertFromJson(byte[] result) {
            return new JsonEntry(KEY, VALUE);
        }

        @Override
        protected FileChannel getFileChannel() throws IOException {
            fileChannelMock = mock(FileChannel.class);
            return fileChannelMock;
        }
    };

    @Before
    public void before() {
        positionMock = createPositionMock();
    }

    @Test
    public void shouldReturnValueWhenCallReadWithPosition() throws IOException {

        String value = fileProviderReader.read(positionMock);

        verify(fileChannelMock).position(OFFSET);
        assertEquals(VALUE, value);
    }

    @Test(expected = FileReadException.class)
    public void shouldThrowFileReadExceptionWhenCallReadWithPositionAndThrowIOException() throws IOException {
        when(fileChannelMock.read(any(ByteBuffer.class))).thenThrow(new IOException());

        fileProviderReader.read(positionMock);
    }

    @Test
    public void shouldReturnAllFileContentWhenReadAll() throws IOException {
        fileProviderReader.readAll();

        verify(fileChannelMock).read(any(ByteBuffer.class));
    }

    @Test(expected = FileReadException.class)
    public void shouldThrowFileReadExceptionWhenCallReadAllAndThrowIOException() throws IOException {
        when(fileChannelMock.read(any(ByteBuffer.class))).thenThrow(new IOException());

        fileProviderReader.readAll();
    }

    private Position createPositionMock() {
        Position positionMock = mock(Position.class);

        when(positionMock.getOffset()).thenReturn(OFFSET);
        when(positionMock.getSize()).thenReturn(SIZE);

        return positionMock;
    }
}