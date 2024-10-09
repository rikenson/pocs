package com.tiger.pocs.utils.encryption;

import lombok.Getter;

import java.util.List;

@Getter
public class VaultEncryptResponse {
    private VaultEncryptData data;

    @Getter
    public static class VaultEncryptData {
        private List<VaultEncryptResult> batchResults;

    }

    @Getter
    public static class VaultEncryptResult {
        private String ciphertext;

    }
}
