package com.tiger.pocs.domain;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;

public record TestSampleRequest(

        @NotNull(message = "Name is mandatory.")
        @Length(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
        String name,

        @Length(min = 15, max = 255, message = "Description must be between 15 and 255 characters.")
        String description,

        @NotNull(message = "Status is mandatory.")
        StatusEnum status,

        @Past(message = "Start date time must be in the past.")
        LocalDateTime startDateTime,

        @FutureOrPresent(message = "Start date time must be in the future.")
        LocalDateTime endDateTime
) implements Serializable {
}


