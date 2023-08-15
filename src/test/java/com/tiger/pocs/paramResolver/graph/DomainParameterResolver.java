package com.tiger.pocs.paramResolver.graph;

import com.tiger.pocs.payload.Domain;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.UUID;

import static com.tiger.pocs.utils.Constants.*;


public class DomainParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == Domain.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {

        return Domain.builder()
                .uuid(UUID_VALUE.toString())
                .code("INV")
                .name("Investment")
                .build();
    }
}
