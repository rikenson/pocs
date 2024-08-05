package labs.rikenson.jungle.kernel.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;


@Slf4j
public class ObjectPropertyValidator {

    private ObjectPropertyValidator() {
    }

    private static final BiFunction<String, Class<?>, Object> convertStringToType = (value, fieldType) -> {
        try {
            if (fieldType == String.class) {
                return value;
            } else if (fieldType == Integer.class) {
                return Integer.valueOf(value);
            } else if (fieldType == Double.class) {
                return Double.valueOf(value);
            } else if (fieldType == Boolean.class) {
                return Boolean.valueOf(value);
            } else if (fieldType == LocalDate.class) {
                return LocalDate.parse(value);
            } else if (fieldType == LocalDateTime.class) {
                return LocalDateTime.parse(value);
            } else {
                return null;
            }
        } catch (NumberFormatException | DateTimeParseException e) {
            return null;
        }
    };

    private static final BiPredicate<String, FieldRules> validateString = (value, rules) -> {
        if (StringUtils.isEmpty(value) && rules.nullable) return false;
        if (rules.minLength != null || rules.maxLength != null) {
            var isMinOk = true;
            var isMaxOk = true;
            if (rules.minLength != null)
                isMinOk = value.length() > rules.minLength;
            if (rules.maxLength != null)
                isMaxOk = value.length() < rules.maxLength;
            return isMinOk && isMaxOk;
        }
        return true;
    };

    private static final BiPredicate<Integer, FieldRules> validateInteger = (value, rules) -> {
        Integer minValue = rules.minLength;
        Integer maxValue = rules.maxLength;
        if (minValue != null && value < minValue) return false;
        return maxValue == null || value <= maxValue;
    };


    private static final BiPredicate<Object, FieldRules> validateField = (fieldValue, rules) -> {
        if (fieldValue == null) return rules.nullable;
        var verdict = true;
        Class<?> fieldType = rules.fieldType;
        if (fieldType == String.class)
            verdict = validateString.test((String) fieldValue, rules);
        if (fieldType == Integer.class)
            verdict = validateInteger.test((Integer) fieldValue, rules);
        return verdict;
    };

    public static final BiPredicate<Object, Map<String, FieldRules>> validate = (obj, fieldRules) -> {
        if (obj == null || fieldRules == null) {
            throw new IllegalArgumentException("L'objet et les règles de validation ne doivent pas être null.");
        }

        Class<?> objClass = obj.getClass();

        for (Map.Entry<String, FieldRules> entry : fieldRules.entrySet()) {
            String fieldName = entry.getKey();
            FieldRules rules = entry.getValue();

            try {
                Field field = objClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                String fieldValueStr = (String) field.get(obj);
                Object fieldValue = convertStringToType.apply(fieldValueStr, rules.fieldType);
                if (fieldValue == null) {
                    return false;
                }
                if (!validateField.test(fieldValue, rules)) {
                    return false;
                }

            } catch (NoSuchFieldException | IllegalAccessException e) {
                log.error("Exception: {}", e.getMessage());
                return false;
            }
        }
        return true;
    };

}

