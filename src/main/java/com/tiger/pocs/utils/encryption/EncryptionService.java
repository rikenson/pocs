package com.tiger.pocs.utils.encryption;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

import java.util.Base64;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EncryptionService {

    private final VaultTemplate vaultTemplate;

    @Value("${vault.encrypt-key}")
    private String encryptKey;

    @Value("${vault.encrypt-path}")
    private String encryptPath;


    public String encrypt(String plaintext) {
        return handleEncryption(() -> {
            String encodedPlaintext = Base64.getEncoder().encodeToString(plaintext.getBytes());
            Map<String, String> request = Map.of("plaintext", encodedPlaintext);
            VaultResponse response = vaultTemplate.write(encryptPath, request);
            return Optional.ofNullable(response)
                    .map(VaultResponse::getData)
                    .map(data -> (String) data.get("ciphertext"))
                    .orElseThrow(() -> new VaultEncryptionException("Ciphertext not found in the response from Vault."));
        });
    }

    public String decrypt(String ciphertext) {
        var decodeValue = vaultTemplate.opsForTransit().decrypt(encryptKey, ciphertext);
        return handleDecryption(() -> decodeValue);
    }

    private String handleEncryption(SupplierWithException<String> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            throw new VaultEncryptionException("Error while encrypting data: " + e.getMessage(), e);
        }
    }

    private String handleDecryption(SupplierWithException<String> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            throw new VaultDecryptionException("Error while decrypting data: " + e.getMessage(), e);
        }
    }

    @FunctionalInterface
    private interface SupplierWithException<T> {
        T get() throws VaultDecryptionException, VaultEncryptionException;
    }
}
