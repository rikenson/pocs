package com.tiger.pocs.domain.entity.rdbms;

import com.tiger.pocs.domain.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Table(name = "sample")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SampleEntity extends BaseEntity {

    @Length(min = 3, max = 30, message = "Name must be between 3 and 30 characters.")
    private String name;

    @Length(min = 3, max = 30, message = "Description must be between 15 and 500 characters.")
    private String description;

    @NotNull(message = "Start date time is mandatory.")
    private LocalDateTime startDateTime;

    @FutureOrPresent(message = "End date time must be in the future or present.")
    private LocalDateTime endDateTime;

    @NotNull(message = "Status is mandatory.")
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;
}
