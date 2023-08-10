package com.tiger.pocs.paramResolver;

import com.tiger.pocs.payload.ParticipantResponse;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import static com.tiger.pocs.utils.Constants.*;

public class ParticipantResponseParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == ParticipantResponse.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        var response = new ParticipantResponse();

        response.setUuid(UUID_VALUE.toString());
        response.setVersion(VERSION);
        response.setFirstname(PARTICIPANT_FIRST_NAME);
        response.setLastname(PARTICIPANT_LAST_NAME);
        response.setRole(ParticipantResponse.RoleEnum.UNKNOWN);
        response.setCreatedByUser(CREATE_BY_USER);
        response.setModifiedByUser(MODIFIED_BY_USER);
        response.setCreatedAt(CREATED_DATE_TIME);
        response.setUpdatedAt(UPDATED_DATE_TIME);
        return response;
    }
}
