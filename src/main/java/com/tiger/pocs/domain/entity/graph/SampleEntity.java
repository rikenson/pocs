package com.tiger.pocs.domain.entity.graph;

import com.tiger.pocs.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @SuperBuilder
public class SampleEntity extends BaseEntity {
    private String name;
    private String description;
//    @SerializedName("start_date_time")
    private LocalDateTime startDateTime;
//    @SerializedName("end_date_time")
    private LocalDateTime endDateTime;
    private Status status = Status.PENDING;
}
