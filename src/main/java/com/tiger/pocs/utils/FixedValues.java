package com.tiger.pocs.utils;

import java.time.LocalDateTime;

public class FixedValues {

    private FixedValues() {
    }

    public static final String LOCAL_ENV = "local";
    public static final String DEV_ENV = "dev";
    public static final String TEST_ENV = "test";
    public static final String NOT_FOUND_EXCEPTION_MSG = "Entity with id: %s could not be found";
    public static final String DEFAULT_CREATE_BY_USER = "Anonymous";
    public static final LocalDateTime CURRENT_DATE_TIME = LocalDateTime.now();
    public static final String DEFAULT_MODIFIED_BY_USER = "Anonymous";



}
