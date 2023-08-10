package com.tiger.pocs.paramResolver;

import com.tiger.pocs.payload.ParticipantRequest;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import static com.tiger.pocs.utils.Constants.*;


public class ParticipantRequestParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == ParticipantRequest.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {

        var model = new ParticipantRequest();
        model.setFirstname(PARTICIPANT_FIRST_NAME);
        model.setLastname(PARTICIPANT_LAST_NAME);
        model.setRole(ParticipantRequest.RoleEnum.valueOf(PARTICIPANT_ROLE.toString()));
        return model;
    }
}
