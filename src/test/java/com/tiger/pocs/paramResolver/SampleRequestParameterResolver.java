package com.tiger.pocs.paramResolver;

import com.tiger.pocs.payload.SampleRequest;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import static com.tiger.pocs.utils.Constants.*;


public class SampleRequestParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == SampleRequest.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {

        var model = new SampleRequest();
        model.setName(SAMPLE_NAME);
        model.setDescription(SAMPLE_DESCRIPTION);
        model.setStatus(SAMPLE_STATUS);
        model.setStartDateTime(SAMPLE_START_DATE_TIME);
        model.setEndDateTime(SAMPLE_END_DATE_TIME);
        return model;
    }
}
