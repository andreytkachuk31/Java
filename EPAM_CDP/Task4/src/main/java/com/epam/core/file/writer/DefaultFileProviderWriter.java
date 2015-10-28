package com.epam.core.file.writer;


import com.epam.core.exception.FileWriteException;
import com.epam.core.file.json.JsonEntry;
import com.epam.core.file.position.Position;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * @author Andrii_Tkachuk
 * @since 10/21/2015
 */
public class DefaultFileProviderWriter implements FileProviderWriter {

    private Path path;

    @Override
    public Position append(String key, String value) {
        try {
            FileChannel fileChannel = getFileChannel();
            long offset = fileChannel.size();
            int size = writeJsonToFile(key, value);
            return new Position(offset, size);
        } catch (IOException e) {
            throw new FileWriteException(e);
        }
    }

    @Override
    public void remove(Position position) {
        try {
            FileChannel fileChannel = getFileChannel();
            byte[] result = new byte[position.getSize()];
            fileChannel.write(ByteBuffer.wrap(result), position.getOffset());
        } catch (IOException e) {
            throw new FileWriteException(e);
        }
    }

    @Override
    public void write(String text) {
        try {
            FileWriter fileWriter = new FileWriter(path.toFile());
            fileWriter.write(EMPTY);
            fileWriter.write(text);
            fileWriter.flush();
        } catch (IOException e) {
            throw new FileWriteException(e);
        }
    }

    private int writeJsonToFile(String key, String value) throws IOException {
        FileChannel fileChannel = getFileChannel();
        Gson gson = new Gson();
        String result = gson.toJson(new JsonEntry(key, value));
        byte[] bytes = result.getBytes();
        fileChannel.write(ByteBuffer.wrap(bytes));
        return bytes.length;
    }

    protected FileChannel getFileChannel() throws IOException {
        return FileChannel.open(path, StandardOpenOption.APPEND);
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
