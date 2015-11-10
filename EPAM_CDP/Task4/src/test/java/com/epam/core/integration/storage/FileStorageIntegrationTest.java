package com.epam.core.integration.storage;

import com.epam.bootstrap.builder.FileStorageBuilder;
import com.epam.bootstrap.builder.StorageBuilder;
import com.epam.core.unit.storage.Storage;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

/**
 * @author Andrii_Tkachuk
 * @since 11/6/2015
 */
public class FileStorageIntegrationTest {

    private static final String TEST_FILE_PREFIX = "test_storage";
    private static final String TEST_FILE_SUFFIX = ".txt";

    private static final String KEY = "key";
    private static final String VALUE = "value";
    private static final String UPDATE_VALUE = "updateValue";

    private Path path;

    private Storage storage;

    @Before
    public void before() throws IOException {
        path = Files.createTempFile(TEST_FILE_PREFIX, TEST_FILE_SUFFIX);
        StorageBuilder storageBuilder = new FileStorageBuilder(path);
        storage = storageBuilder.build();
    }

    @After
    public void after() throws IOException {
        Files.deleteIfExists(path);
    }

    @Test
    public void shouldCreate() {
        storage.create(KEY, VALUE);

        storage.read(KEY);
    }

    @Test
    public void shouldUpdate() {
        storage.create(KEY, VALUE);

        storage.update(KEY, UPDATE_VALUE);

        String value = storage.read(KEY);

        assertEquals(value, UPDATE_VALUE);
    }

    @Test
    public void shouldRemove() {
        storage.create(KEY, VALUE);

        storage.remove(KEY);
    }
}
