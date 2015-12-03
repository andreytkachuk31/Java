package com.epam.core.unit.file.reader;

import com.epam.core.unit.file.position.Position;

/**
 * @author Andrii_Tkachuk
 * @since 10/21/2015
 */
public interface FileProviderReader {

    String read(Position position);

    byte[] readAll();
}
