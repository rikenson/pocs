package com.tiger.pocs.converter;

import jakarta.validation.constraints.NotNull;
import org.springframework.core.convert.converter.Converter;
import software.amazon.awssdk.services.ssm.model.ParameterType;

public class StringToParameterType implements Converter<String, ParameterType> {
    @Override
    public ParameterType convert(@NotNull final String source) {
        try {
            return ParameterType.valueOf(source);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
