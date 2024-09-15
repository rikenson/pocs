package com.tiger.pocs.utils.encryption;

public class VaultEncryptionException extends RuntimeException {

    public VaultEncryptionException(String message) {
        super(message);
    }

    public VaultEncryptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
