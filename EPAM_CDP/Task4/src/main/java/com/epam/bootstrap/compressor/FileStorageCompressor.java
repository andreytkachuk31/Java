package com.epam.bootstrap.compressor;

import com.epam.core.storage.FileStorage;

import java.io.ByteArrayOutputStream;

/**
 * @author Andrii_Tkachuk
 * @since 10/27/2015
 */
public class FileStorageCompressor implements StorageCompressor {

    private FileStorage fileStorage;

    public FileStorageCompressor(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    @Override
    public void compress() {
        byte[] bytesFromFile = fileStorage.getFileProviderReader().readAll();
        String compressContentFile = compressContent(bytesFromFile);
        fileStorage.getFileProviderWriter().write(compressContentFile);
    }


    private String compressContent(byte[] bytesFromFile) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (byte b : bytesFromFile) {
            if (b != 0) {
                baos.write(b);
            }
        }
        return new String(baos.toByteArray());
    }
}
