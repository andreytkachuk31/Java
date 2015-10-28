package com.epam.core.file.reader;


import com.epam.core.exception.FileReadException;
import com.epam.core.file.json.JsonEntry;
import com.epam.core.file.position.Position;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * @author Andrii_Tkachuk
 * @since 10/21/2015
 */
public class DefaultFileProviderReader implements FileProviderReader {

    private Path path;

    @Override
    public String read(Position position) {
        try {
            byte[] result = new byte[position.getSize()];
            FileChannel fileChannel = getFileChannel();
            fileChannel.position(position.getOffset());
            fileChannel.read(ByteBuffer.wrap(result));
            JsonEntry jsonEntry = convertFromJson(result);
            return jsonEntry.getValue();
        } catch (IOException e) {
            throw new FileReadException(e);
        }
    }

    @Override
    public byte[] readAll() {
        try {
            FileChannel fileChannel = getFileChannel();
            byte[] bytes = new byte[(int) fileChannel.size()];
            fileChannel.read(ByteBuffer.wrap(bytes));
            return bytes;
        } catch (IOException e) {
            throw new FileReadException(e);
        }
    }

    protected JsonEntry convertFromJson(byte[] result) {
        String jsonString = new String(result);
        Gson gson = new Gson();
        return gson.fromJson(jsonString, JsonEntry.class);
    }

    protected FileChannel getFileChannel() throws IOException {
        return FileChannel.open(path, StandardOpenOption.READ);
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
