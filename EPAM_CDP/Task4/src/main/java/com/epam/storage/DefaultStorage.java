package com.epam.storage;

import com.epam.file.index.DefaultIndex;
import com.epam.file.index.Index;
import com.epam.file.position.Position;
import com.epam.file.reader.DefaultFileProviderReader;
import com.epam.file.reader.FileProviderReader;
import com.epam.file.writer.DefaultFileProviderWriter;
import com.epam.file.writer.FileProviderWriter;

import java.nio.file.Path;

/**
 * @author Andrii_Tkachuk
 * @since 10/21/2015
 */
public class DefaultStorage implements Storage {

    private FileProviderReader fileProviderReader;
    private FileProviderWriter fileProviderWriter;
    private Index index;

    public DefaultStorage(Path path) {
        this.fileProviderReader = new DefaultFileProviderReader(path);
        this.fileProviderWriter = new DefaultFileProviderWriter(path);
        this.index = new DefaultIndex();
    }

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

        index.remove(key);

        return result;
    }
}
