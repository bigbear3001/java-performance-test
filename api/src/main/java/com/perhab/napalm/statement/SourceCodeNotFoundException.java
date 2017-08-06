package com.perhab.napalm.statement;

/**
 * Exception that is thrown if we cannot find or read the source code.
 */
public class SourceCodeNotFoundException extends RuntimeException {
    public SourceCodeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
