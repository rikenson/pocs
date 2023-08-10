package com.tiger.pocs.utils;

import com.tiger.pocs.payload.*;
import com.tiger.pocs.domain.enums.ParticipantRole;

import java.time.LocalDateTime;
import java.util.UUID;

public class Constants {

    public static final UUID UUID_VALUE = UUID.fromString("d815d301-8bae-4dd0-a292-56e0cb7509d6");
    public static final String WORKSHOP_NAME = "Unit Testing Essential Training";
    public static final String UPDATED_WORKSHOP_NAME = "Unit Testing Essential Updated";
    public static final String WORKSHOP_DESCRIPTION = "Learn unit testing with JUnit, AssertJ, Mockito, Pitest";
    public static final WorkshopRequest.StatusEnum WORKSHOP_STATUS = WorkshopRequest.StatusEnum.PENDING;
    public static final UpdatedWorkshopRequest.StatusEnum UPDATED_WORKSHOP_STATUS = UpdatedWorkshopRequest.StatusEnum.PENDING;


    public static final String PARTICIPANT_FIRST_NAME = "Wisly";
    public static final String PARTICIPANT_LAST_NAME = "Clervilus";
    public static final ParticipantRole PARTICIPANT_ROLE = ParticipantRole.UNKNOWN;
    public static final UpdatedParticipantRequest.RoleEnum UPDATED_PARTICIPANT_ROLE = UpdatedParticipantRequest.RoleEnum.UNKNOWN;


    public static final LocalDateTime WORKSHOP_START_DATE_TIME = LocalDateTime.parse("2023-06-08T19:53:14.263399");
    public static final LocalDateTime WORKSHOP_END_DATE_TIME = LocalDateTime.parse("2023-06-08T19:53:14.263399");
    public static final LocalDateTime CREATED_DATE_TIME = LocalDateTime.parse("2023-06-08T19:53:14.263399");
    public static final LocalDateTime UPDATED_DATE_TIME = LocalDateTime.parse("2023-06-08T19:53:14.263399");
    public static final String CREATE_BY_USER = "Anonymous";
    public static final String MODIFIED_BY_USER = "Anonymous";
    public static final int VERSION = 0;

}
