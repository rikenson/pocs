package com.tiger.pocs.utils;


import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ValidationUtil {

    private final Validator validator;

    public ValidationUtil() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public <T> Map<String, String> validate(T request) {
        var violations = validator.validate(request);
        var errors = new HashMap<String, String>();
        if (!violations.isEmpty()) {
            violations.forEach(violation -> errors.put(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return errors;
    }
}
