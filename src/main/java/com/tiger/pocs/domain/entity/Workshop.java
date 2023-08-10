package com.tiger.pocs.domain.entity;

import com.tiger.pocs.domain.enums.WorkshopStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "Team")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @SuperBuilder
public class Workshop extends BaseEntity {
    private String name;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    @Builder.Default
    private WorkshopStatus status = WorkshopStatus.PENDING;
    @Builder.Default
    @OneToMany(cascade = CascadeType.DETACH)
    private List<Participant> participants = new ArrayList<>();
}
