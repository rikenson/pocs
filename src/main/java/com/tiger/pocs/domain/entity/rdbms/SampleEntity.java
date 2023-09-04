package com.tiger.pocs.domain.entity.rdbms;

import com.tiger.pocs.domain.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;
}
