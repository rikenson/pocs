package com.tiger.pocs.domain.filter;

import com.tiger.pocs.domain.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class SampleFilter implements Serializable {

    private static final String PREFERRED_FIELD_VALUE = "name";
    private String name;
    private Status status;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    @Builder.Default
    private String preferredField = PREFERRED_FIELD_VALUE;
}

