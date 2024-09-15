package com.tiger.pocs.utils.encryption;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CustomerEncoder {

    private final EncryptionService encryptionService;

    public <T> T encodeProperties(T object, Map<String, Integer> propertiesToEncode) throws PropertyProcessingException {
        propertiesToEncode.forEach((propertyName, encodingLevel) -> {
            try {
                processProperty(object, propertyName, encodingLevel);
            } catch (Exception e) {
                throw new PropertyProcessingException("Erreur lors du traitement de la propriété " + propertyName, e);
            }
        });
        return object;
    }

    public <T> T decodeProperties(T object, Map<String, Integer> propertiesToDecode) throws PropertyProcessingException {
        propertiesToDecode.forEach((propertyName, encodingLevel) -> {
            try {
                processPropertyForDecoding(object, propertyName, encodingLevel);
            } catch (Exception e) {
                throw new PropertyProcessingException("Erreur lors du traitement de la propriété " + propertyName, e);
            }
        });
        return object;
    }

    private <T> void processProperty(T object, String propertyName, int encodingLevel)
            throws UnsupportedEncodingLevelException, IllegalAccessException, NoSuchFieldException {
        String[] propertyPath = propertyName.split("\\.");
        Object current = object;
        for (int i = 0; i < propertyPath.length; i++) {
            current = processField(current, propertyPath[i], i == propertyPath.length - 1, encodingLevel);
        }
    }

    private <T> void processPropertyForDecoding(T object, String propertyName, int encodingLevel)
            throws UnsupportedEncodingLevelException, IllegalAccessException, NoSuchFieldException {
        String[] propertyPath = propertyName.split("\\.");
        Object current = object;
        for (int i = 0; i < propertyPath.length; i++) {
            current = processFieldForDecoding(current, propertyPath[i], i == propertyPath.length - 1, encodingLevel);
        }
    }

    private Object processField(Object currentObject, String fieldName, boolean isLastField, int encodingLevel)
            throws UnsupportedEncodingLevelException, IllegalAccessException, NoSuchFieldException {
        Field field = getField(currentObject.getClass(), fieldName);
        field.setAccessible(true);
        if (isLastField) {
            encodeFinalField(currentObject, field, encodingLevel);
        } else {
            currentObject = field.get(currentObject);
        }
        return currentObject;
    }

    private Object processFieldForDecoding(Object currentObject, String fieldName, boolean isLastField, int encodingLevel)
            throws UnsupportedEncodingLevelException, IllegalAccessException, NoSuchFieldException {
        Field field = getField(currentObject.getClass(), fieldName);
        field.setAccessible(true);
        if (isLastField) {
            decodeFinalField(currentObject, field, encodingLevel);
        } else {
            currentObject = field.get(currentObject);
        }
        return currentObject;
    }

    private void encodeFinalField(Object currentObject, Field field, int encodingLevel)
            throws UnsupportedEncodingLevelException, IllegalAccessException {
        Object value = field.get(currentObject);
        if (value instanceof String clearValue) {
            String encodedValue = switch (encodingLevel) {
                case 1 -> encode(clearValue);
                case 2 -> encryptionService.encrypt(clearValue);
                case 3 -> tokenize(clearValue);
                default ->
                        throw new UnsupportedEncodingLevelException("Niveau de codage non supporté : " + encodingLevel);
            };
            field.set(currentObject, encodedValue);
        }
    }

    private void decodeFinalField(Object currentObject, Field field, int encodingLevel)
            throws UnsupportedEncodingLevelException, IllegalAccessException {
        Object value = field.get(currentObject);
        if (value instanceof String encodedValue) {
            String decodedValue = switch (encodingLevel) {
                case 1 -> decode(encodedValue);
                case 2 -> encryptionService.decrypt(encodedValue);
                case 3 -> detokenize(encodedValue);
                default ->
                        throw new UnsupportedEncodingLevelException("Niveau de décodage non supporté : " + encodingLevel);
            };
            field.set(currentObject, decodedValue);
        }
    }

    private Field getField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        return clazz.getDeclaredField(fieldName);
    }

    private String encode(String clearValue) {
        return Base64.getEncoder().encodeToString(clearValue.getBytes());
    }

    private String decode(String encodedValue) {
        return new String(Base64.getDecoder().decode(encodedValue));
    }

    private String tokenize(String clearValue) {
        return "TOKEN_" + clearValue;
    }

    private String detokenize(String tokenizedValue) {
        if (tokenizedValue.startsWith("TOKEN_")) {
            return tokenizedValue.substring(6);
        } else {
            throw new IllegalArgumentException("Token invalide : " + tokenizedValue);
        }
    }
}
