package com.tiger.pocs.utils.encryption;


import java.lang.reflect.Field;
import java.util.Base64;
import java.util.Map;

public class CustomerEncoder {

    private CustomerEncoder() {
    }

    public static <T> T encodeProperties(T object, Map<String, Integer> propertiesToEncode) throws PropertyProcessingException {
        propertiesToEncode.forEach((propertyName, encodingLevel) -> {
            try {
                processProperty(object, propertyName, encodingLevel);
            } catch (Exception e) {
                throw new PropertyProcessingException("Erreur lors du traitement de la propriété " + propertyName, e);
            }
        });
        return object;
    }

    private static <T> void processProperty(T object, String propertyName, int encodingLevel) throws Exception {
        String[] propertyPath = propertyName.split("\\.");
        Object currentObject = object;
        for (int i = 0; i < propertyPath.length; i++) {
            currentObject = processField(currentObject, propertyPath[i], i == propertyPath.length - 1, encodingLevel);
        }
    }

    private static Object processField(Object currentObject, String fieldName, boolean isLastField, int encodingLevel) throws Exception {
        Field field = getField(currentObject.getClass(), fieldName);
        field.setAccessible(true);
        if (isLastField) {
            encodeFinalField(currentObject, field, encodingLevel);
        } else {
            currentObject = field.get(currentObject);
        }
        return currentObject;
    }

    private static void encodeFinalField(Object currentObject, Field field, int encodingLevel) throws Exception {
        Object value = field.get(currentObject);
        if (value instanceof String clearValue) {
            String encodedValue = switch (encodingLevel) {
                case 1 -> encrypt(clearValue);
                case 2 -> encode(clearValue);
                case 3 -> tokenize(clearValue);
                default ->
                        throw new UnsupportedEncodingLevelException("Niveau de codage non supporté : " + encodingLevel);
            };
            field.set(currentObject, encodedValue);
        }
    }

    private static Field getField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        return clazz.getDeclaredField(fieldName);
    }

    private static String encrypt(String clearValue) {
        return Base64.getEncoder().encodeToString(clearValue.getBytes());
    }

    private static String encode(String clearValue) {
        return Base64.getEncoder().encodeToString(clearValue.getBytes());
    }

    private static String tokenize(String clearValue) {
        return "TOKEN_" + clearValue;
    }

}
