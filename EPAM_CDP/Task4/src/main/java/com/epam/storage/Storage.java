package com.epam.storage;

/**
 * @author Andrii_Tkachuk
 * @since 10/21/2015
 */
public interface Storage {

    void create(String key, String value);

    String read(String key);

    String update(String key, String value);

    String remove(String key);
    
}
