package com.epam.bootstrap.runner;

import com.epam.bootstrap.builder.FileStorageBuilder;
import com.epam.bootstrap.builder.StorageBuilder;
import com.epam.bootstrap.compressor.FileStorageCompressor;
import com.epam.core.storage.FileStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Andrii_Tkachuk
 * @since 10/27/2015
 */
public class FileRunner implements Runner {

    private static final String STORAGE_FILE_NAME = "storage.txt";

    private StorageBuilder fileStorageBuilder;

    public static Runner createInstance() throws IOException {
        Path path = Paths.get(STORAGE_FILE_NAME);
        Files.deleteIfExists(path);
        Files.createFile(path);

        FileRunner fileRunner = new FileRunner();
        fileRunner.setFileStorageBuilder(new FileStorageBuilder(path));

        return fileRunner;
    }

    @Override
    public void run() {
        FileStorage storage = (FileStorage) fileStorageBuilder.build();

        storage.create("key1", "value1");
        storage.create("key2", "value2");
        storage.create("key3", "value3");

        System.out.println("-----CREATE-------");
        System.out.println("key2 => " + storage.read("key2"));

        storage.update("key2", "updateValue2");

        System.out.println("-----UPDATE-------");
        System.out.println("key2=>" + storage.read("key2"));

        System.out.println("-----REMOVE-------");
        storage.remove("key1");

        new FileStorageCompressor(storage).compress();
    }

    public void setFileStorageBuilder(StorageBuilder fileStorageBuilder) {
        this.fileStorageBuilder = fileStorageBuilder;
    }
}

