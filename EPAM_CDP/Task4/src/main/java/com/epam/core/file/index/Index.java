package com.epam.core.file.index;

import com.epam.core.file.position.Position;

import java.util.Map;

/**
 * @author Andrii_Tkachuk
 * @since 10/22/2015
 */
public interface Index {

    void add(String key, Position position);

    Position get(String key);

    Position remove(String key);

    Map<String, Position> getIndexMap();
}
