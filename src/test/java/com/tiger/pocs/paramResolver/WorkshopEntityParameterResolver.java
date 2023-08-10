package com.tiger.pocs.paramResolver;

import com.tiger.pocs.domain.entity.Workshop;
import com.tiger.pocs.domain.enums.WorkshopStatus;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import static com.tiger.pocs.utils.Constants.*;

public class WorkshopEntityParameterResolver implements ParameterResolver {


    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == Workshop.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        var response = new Workshop();

        response.setUuid(UUID_VALUE);
        response.setName(WORKSHOP_NAME);
        response.setDescription(WORKSHOP_DESCRIPTION);
        response.setStatus(WorkshopStatus.valueOf(WORKSHOP_STATUS.getValue()));
        response.setStartDateTime(WORKSHOP_START_DATE_TIME);
        response.setEndDateTime(WORKSHOP_END_DATE_TIME);
        response.setCreatedByUser(CREATE_BY_USER);
        return response;
    }
}
