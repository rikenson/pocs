package com.tiger.pocs.domain.entity;

import com.tiger.pocs.domain.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Entity
@Table(name = "sample")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @SuperBuilder
public class SampleEntity extends BaseEntity {
    private String name;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Status status = Status.PENDING;
}
