package com.epam.exception;

/**
 * @author Andrii_Tkachuk
 * @since 10/23/2015
 */
public class NoSuchIndexException extends RuntimeException {

    public NoSuchIndexException(String message) {
        super(message);
    }
}
