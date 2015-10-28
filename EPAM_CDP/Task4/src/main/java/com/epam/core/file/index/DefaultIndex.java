package com.epam.core.file.index;

import com.epam.core.exception.NoSuchIndexException;
import com.epam.core.file.position.Position;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Andrii_Tkachuk
 * @since 10/22/2015
 */
public class DefaultIndex implements Index {

    private Map<String, Position> indexMap;

    public DefaultIndex() {
        this.indexMap = new HashMap<String, Position>();
    }

    @Override
    public void add(String key, Position position) {
        indexMap.put(key, position);
    }

    @Override
    public Position get(String key) {
        if (indexMap.containsKey(key))
            return indexMap.get(key);
        throw new NoSuchIndexException("No key '" + key + "' in storage");
    }

    @Override
    public Position remove(String key) {
        if (indexMap.containsKey(key))
            return indexMap.remove(key);
        throw new NoSuchIndexException("No key '" + key + "' in storage");
    }

    @Override
    public Map<String, Position> getIndexMap() {
        return indexMap;
    }
}
