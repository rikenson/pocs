package com.tmt.demographics.entity;

import com.tmt.demographics.enums.HttpVerb;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ApiRoute extends AuditMetadata {

    @Id
    private UUID currentId = UUID.randomUUID();
    private String name;
    private String path;
    private HttpVerb verb;
    private String uri;
}
