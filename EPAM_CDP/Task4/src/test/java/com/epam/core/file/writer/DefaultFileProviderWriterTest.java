package com.epam.core.file.writer;

import com.epam.core.file.position.Position;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultFileProviderWriterTest {

    private static final String KEY = "key";
    private static final String VALUE = "value";

    private static final long OFFSET = 10;
    private static final int SIZE = 8;

    private Position positionMock;

    private FileChannel fileChannelMock;

    @Spy
    private DefaultFileProviderWriter fileProviderWriter = new DefaultFileProviderWriter() {

        @Override
        protected int writeJsonToFile(String key, String value) throws IOException {
            return SIZE;
        }

        @Override
        protected FileChannel getFileChannel() throws IOException {
            fileChannelMock = mock(FileChannel.class);
            when(fileChannelMock.size()).thenReturn(OFFSET);
            return fileChannelMock;
        }
    };

    @Before
    public void before() {
        positionMock = createPositionMock();
    }

    @Test
    public void shouldReturnPositionWhenCallAppendWithKeyAndValue() throws IOException {

        Position position = fileProviderWriter.append(KEY, VALUE);

        assertEquals(new Position(OFFSET, SIZE), position);
    }

    @Test
    public void shouldRemoveByPositionWhenCallRemove() throws IOException {

        fileProviderWriter.remove(positionMock);

        verify(fileChannelMock).write(ByteBuffer.wrap(new byte[positionMock.getSize()]), positionMock.getOffset());
    }

    private Position createPositionMock() {
        Position positionMock = mock(Position.class);

        when(positionMock.getOffset()).thenReturn(OFFSET);
        when(positionMock.getSize()).thenReturn(SIZE);

        return positionMock;
    }
}