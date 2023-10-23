package com.tiger.pocs.domain.entity.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter @AllArgsConstructor @SuperBuilder
public abstract class BaseEntity implements Serializable {

    private String uuid;
//    @SerializedName("created_at")
    private LocalDateTime createdAt;
//    @SerializedName("modified_at")
    private LocalDateTime modifiedAt;
//    @SerializedName("created_by")
    private String createdBy;
//    @SerializedName("modified_by")
    private String modifiedBy;

    protected BaseEntity() {
        this.uuid = UUID.randomUUID().toString();
    }
}
