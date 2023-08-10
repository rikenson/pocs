package com.tiger.pocs.paramResolver;

import com.tiger.pocs.payload.UpdatedParticipantRequest;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import static com.tiger.pocs.utils.Constants.*;


public class UdatedParticipantRequestParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == UpdatedParticipantRequest.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {

        var model = new UpdatedParticipantRequest();
        model.setUuid(UUID_VALUE.toString());
        model.setFirstname(PARTICIPANT_FIRST_NAME);
        model.setLastname(PARTICIPANT_LAST_NAME);
        model.setRole(UPDATED_PARTICIPANT_ROLE);
        model.setCreatedByUser(CREATE_BY_USER);
        model.setModifiedByUser(MODIFIED_BY_USER);
        model.setCreatedAt(CREATED_DATE_TIME);
        model.setUpdatedAt(UPDATED_DATE_TIME);
        return model;
    }
}
