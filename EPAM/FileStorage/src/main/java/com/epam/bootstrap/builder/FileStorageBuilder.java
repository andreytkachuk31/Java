package com.epam.bootstrap.builder;

import com.epam.core.unit.file.index.DefaultIndex;
import com.epam.core.unit.file.reader.DefaultFileProviderReader;
import com.epam.core.unit.file.writer.DefaultFileProviderWriter;
import com.epam.core.unit.storage.FileStorage;
import com.epam.core.unit.storage.Storage;

import java.nio.file.Path;

/**
 * @author Andrii_Tkachuk
 * @since 10/26/2015
 */
public class FileStorageBuilder implements StorageBuilder {

    private Path path;

    public FileStorageBuilder(Path path) {
        this.path = path;
    }

    @Override
    public Storage build() {
        FileStorage storage = new FileStorage();

        DefaultFileProviderWriter fileProviderWriter = new DefaultFileProviderWriter();
        fileProviderWriter.setPath(path);

        DefaultFileProviderReader fileProviderReader = new DefaultFileProviderReader();
        fileProviderReader.setPath(path);

        DefaultIndex index = new DefaultIndex();

        storage.setFileProviderWriter(fileProviderWriter);
        storage.setFileProviderReader(fileProviderReader);
        storage.setIndex(index);

        return storage;
    }
}
