package com.tiger.pocs.paramResolver;

import com.tiger.pocs.payload.WorkshopResponse;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import static com.tiger.pocs.utils.Constants.*;

public class WorkshopResponseParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == WorkshopResponse.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        var model = new WorkshopResponse();

        model.setUuid(UUID_VALUE.toString());
        model.setVersion(VERSION);
        model.setName(WORKSHOP_NAME);
        model.setDescription(WORKSHOP_DESCRIPTION);
        model.setStatus(WorkshopResponse.StatusEnum.valueOf(WORKSHOP_STATUS.getValue()));
        model.setStartDateTime(WORKSHOP_START_DATE_TIME);
        model.setEndDateTime(WORKSHOP_END_DATE_TIME);
        model.setCreatedByUser(CREATE_BY_USER);
        model.setModifiedByUser(MODIFIED_BY_USER);
        model.setCreatedAt(CREATED_DATE_TIME);
        model.setUpdatedAt(UPDATED_DATE_TIME);
        return model;
    }
}
