package com.tiger.pocs.utils.encryption;

public class UnsupportedEncodingLevelException extends IllegalArgumentException {

    public UnsupportedEncodingLevelException(String message) {
        super(message);
    }

    public UnsupportedEncodingLevelException(String message, Throwable cause) {
        super(message, cause);
    }
}
