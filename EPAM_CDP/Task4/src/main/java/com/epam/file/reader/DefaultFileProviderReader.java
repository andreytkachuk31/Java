package com.epam.file.reader;


import com.epam.exception.FileReadException;
import com.epam.exception.FileWriteException;
import com.epam.file.position.Position;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Andrii_Tkachuk
 * @since 10/21/2015
 */
public class DefaultFileProviderReader implements FileProviderReader {

    private Path path;

    public DefaultFileProviderReader(Path path) {
        this.path = path;
    }

    @Override
    public String read(Position position) {
        try {
            byte[] result = new byte[position.getSize()];
            FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ);
            fileChannel.position(position.getOffset());
            fileChannel.read(ByteBuffer.wrap(result));
            return new String(result);
        } catch (IOException e) {
            throw new FileReadException(e);
        }
    }
}
