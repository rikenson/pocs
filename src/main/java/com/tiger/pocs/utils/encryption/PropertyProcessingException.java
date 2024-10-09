package com.tiger.pocs.utils.encryption;

public class PropertyProcessingException extends RuntimeException {

    public PropertyProcessingException(String message) {
        super(message);
    }

    public PropertyProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
