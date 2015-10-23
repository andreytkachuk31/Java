package com.epam.file.writer;

import com.epam.file.position.Position;

/**
 * @author Andrii_Tkachuk
 * @since 10/21/2015
 */
public interface FileProviderWriter {

    Position append(String key, String value);
}
