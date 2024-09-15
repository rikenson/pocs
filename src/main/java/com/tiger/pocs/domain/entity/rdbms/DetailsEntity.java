package com.tiger.pocs.domain.entity.rdbms;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "details")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @SuperBuilder @ToString
public class DetailsEntity extends BaseEntity {
    private String about;
}
