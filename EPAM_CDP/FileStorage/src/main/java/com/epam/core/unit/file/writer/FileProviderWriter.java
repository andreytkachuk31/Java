package com.epam.core.unit.file.writer;

import com.epam.core.unit.file.position.Position;

/**
 * @author Andrii_Tkachuk
 * @since 10/21/2015
 */
public interface FileProviderWriter {

    Position append(String key, String value);

    void remove(Position position);

    void write(String text);
}
