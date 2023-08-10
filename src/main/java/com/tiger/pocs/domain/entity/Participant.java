package com.tiger.pocs.domain.entity;

import com.tiger.pocs.domain.enums.ParticipantRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "participant")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @SuperBuilder
public class Participant extends BaseEntity {
    private String firstname;
    private String lastname;
    @Builder.Default
    private ParticipantRole role = ParticipantRole.UNKNOWN;
}
