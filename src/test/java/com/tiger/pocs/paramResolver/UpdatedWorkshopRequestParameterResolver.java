package com.tiger.pocs.paramResolver;

import com.tiger.pocs.payload.UpdatedWorkshopRequest;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import static com.tiger.pocs.utils.Constants.*;


public class UpdatedWorkshopRequestParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == UpdatedWorkshopRequest.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {

        var model = new UpdatedWorkshopRequest();
        model.setUuid(UUID_VALUE.toString());
        model.setName(WORKSHOP_NAME);
        model.setDescription(WORKSHOP_DESCRIPTION);
        model.setStatus(UPDATED_WORKSHOP_STATUS);
        model.setStartDateTime(WORKSHOP_START_DATE_TIME);
        model.setEndDateTime(WORKSHOP_END_DATE_TIME);
        model.createdAt(CREATED_DATE_TIME);
        model.updatedAt(UPDATED_DATE_TIME);
        model.setCreatedByUser(CREATE_BY_USER);
        model.setModifiedByUser(MODIFIED_BY_USER);
        return model;
    }
}
