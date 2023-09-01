package com.tiger.pocs.utils;

import com.tiger.pocs.payload.*;

import java.time.LocalDateTime;
import java.util.UUID;

public class Constants {

    public static final String UUID_VALUE = "d815d301-8bae-4dd0-a292-56e0cb7509d6";
    public static final String SAMPLE_NAME = "Unit Testing Essential Training";
    public static final String UPDATED_SAMPLE_NAME = "Unit Testing Essential Updated";
    public static final String SAMPLE_DESCRIPTION = "Learn unit testing with JUnit, AssertJ, Mockito, Pitest";
    public static final SampleRequest.StatusEnum SAMPLE_STATUS = SampleRequest.StatusEnum.PENDING;
    public static final UpdatedSampleRequest.StatusEnum UPDATED_SAMPLE_STATUS = UpdatedSampleRequest.StatusEnum.PENDING;



    public static final LocalDateTime SAMPLE_START_DATE_TIME = LocalDateTime.parse("2023-06-08T19:53:14.263399");
    public static final LocalDateTime SAMPLE_END_DATE_TIME = LocalDateTime.parse("2023-06-08T19:53:14.263399");
    public static final LocalDateTime CREATED_DATE_TIME = LocalDateTime.parse("2023-06-08T19:53:14.263399");
    public static final LocalDateTime UPDATED_DATE_TIME = LocalDateTime.parse("2023-06-08T19:53:14.263399");
    public static final String CREATE_BY_USER = "Anonymous";
    public static final String MODIFIED_BY_USER = "Anonymous";

}
