package com.tmt.demographics.entity;

import lombok.*;
import org.springframework.data.annotation.*;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AuditMetadata {

    @CreatedDate
    protected LocalDateTime createdDate;

    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;

    @CreatedBy
    protected String createdByUser;

    @LastModifiedBy
    protected String modifiedByUser;

    @Version
    protected Long version;
}
