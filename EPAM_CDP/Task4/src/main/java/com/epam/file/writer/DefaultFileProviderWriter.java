package com.epam.file.writer;


import com.epam.exception.FileWriteException;
import com.epam.file.position.Position;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Andrii_Tkachuk
 * @since 10/21/2015
 */
public class DefaultFileProviderWriter implements FileProviderWriter {

    private Path path;

    public DefaultFileProviderWriter(Path path) {
        this.path = path;
    }

    @Override
    public Position append(String key, String value) {
        try {
            FileChannel fileChannel = getFileChannel(path);
            long offset = fileChannel.size();
            int size = writeStringToFile(key);
            size += writeStringToFile(value);
            return new Position(offset, size);
        } catch (IOException e) {
            throw new FileWriteException(e);
        }
    }

    private int writeStringToFile(String text) throws IOException {
        FileChannel fileChannel = getFileChannel(path);
        byte[] bytes = text.getBytes();
        fileChannel.write(ByteBuffer.wrap(bytes));
        return bytes.length;
    }

    public FileChannel getFileChannel(Path path) throws IOException {
        return FileChannel.open(path, StandardOpenOption.APPEND);
    }
}
