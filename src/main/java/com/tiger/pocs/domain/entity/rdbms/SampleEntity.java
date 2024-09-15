package com.tiger.pocs.domain.entity.rdbms;

import com.tiger.pocs.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Entity
@Table(name = "sample")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @SuperBuilder @ToString
public class SampleEntity extends BaseEntity {
    private String name;
    private String description;
    @OneToOne
    private DetailsEntity details;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;
}
