package labs.rikenson.jungle.kernel.utils;

public class FieldRules {
    public final Class<?> fieldType;
    public final boolean nullable;
    public final Integer minLength;
    public final Integer maxLength;

    public FieldRules(Class<?> fieldType, boolean nullable, Integer minLength, Integer maxLength) {
        this.fieldType = fieldType;
        this.nullable = nullable;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }
}

