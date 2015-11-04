package com.epam.core.storage;

import com.epam.core.file.index.Index;
import com.epam.core.file.position.Position;
import com.epam.core.file.reader.FileProviderReader;
import com.epam.core.file.writer.FileProviderWriter;

/**
 * @author Andrii_Tkachuk
 * @since 10/21/2015
 */
public class FileStorage implements Storage {

    private FileProviderReader fileProviderReader;
    private FileProviderWriter fileProviderWriter;
    private Index index;

    @Override
    public void create(String key, String value) {
        Position position = fileProviderWriter.append(key, value);

        index.add(key, position);
    }

    @Override
    public String read(String key) {
        Position position = index.get(key);

        return fileProviderReader.read(position);
    }

    @Override
    public String update(String key, String value) {
        String result = remove(key);

        create(key, value);

        return result;
    }

    @Override
    public String remove(String key) {
        String result = read(key);

        Position position = index.remove(key);
        fileProviderWriter.remove(position);

        return result;
    }

    public FileProviderReader getFileProviderReader() {
        return fileProviderReader;
    }

    public void setFileProviderReader(FileProviderReader fileProviderReader) {
        this.fileProviderReader = fileProviderReader;
    }

    public FileProviderWriter getFileProviderWriter() {
        return fileProviderWriter;
    }

    public void setFileProviderWriter(FileProviderWriter fileProviderWriter) {
        this.fileProviderWriter = fileProviderWriter;
    }

    public Index getIndex() {
        return index;
    }

    public void setIndex(Index index) {
        this.index = index;
    }
}
