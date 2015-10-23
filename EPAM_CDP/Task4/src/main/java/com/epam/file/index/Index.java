package com.epam.file.index;

import com.epam.file.position.Position;

/**
 * @author Andrii_Tkachuk
 * @since 10/22/2015
 */
public interface Index {

    void add(String key, Position position);

    Position get(String key);

    void remove(String key);
}
