package com.epam.runner;

import com.epam.storage.DefaultStorage;
import com.epam.storage.Storage;

import java.io.File;
import java.io.IOException;

/**
 * @author Andrii_Tkachuk
 * @since 10/21/2015
 */
public class Runner {

    public static void main(String[] args) throws IOException {
        File file = new File("storage.txt");
        file.delete();
        file.createNewFile();

        Storage storage = new DefaultStorage(file.toPath());
        storage.create("key1", "value1");
        storage.create("key2", "value2");
        storage.create("key3", "value3");

        System.out.println("!!!AFTER CREATE!!!");
        System.out.println(storage.read("key3"));

        storage.update("key2", "updateValue2");

        System.out.println("!!!AFTER UPDATE!!!");
        System.out.println(storage.read("key2"));

        System.out.println("!!!AFTER REMOVE!!!");
        storage.remove("key1");

        System.out.println(storage.read("key1"));


    }
}
