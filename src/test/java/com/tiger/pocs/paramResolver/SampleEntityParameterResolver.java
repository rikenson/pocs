package com.tiger.pocs.paramResolver;

import com.tiger.pocs.domain.entity.SampleEntity;
import com.tiger.pocs.domain.enums.Status;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import static com.tiger.pocs.utils.Constants.*;

public class SampleEntityParameterResolver implements ParameterResolver {


    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == SampleEntity.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        var response = new SampleEntity();

        response.setUuid(UUID_VALUE);
        response.setName(SAMPLE_NAME);
        response.setDescription(SAMPLE_DESCRIPTION);
        response.setStatus(Status.valueOf(SAMPLE_STATUS.getValue()));
        response.setStartDateTime(SAMPLE_START_DATE_TIME);
        response.setEndDateTime(SAMPLE_END_DATE_TIME);
        response.setCreatedBy(CREATE_BY_USER);
        return response;
    }
}
