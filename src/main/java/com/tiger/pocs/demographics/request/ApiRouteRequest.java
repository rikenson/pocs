package com.tmt.demographics.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tmt.demographics.enums.HttpVerb;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ApiRouteRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String path;
    private HttpVerb verb;
    @NotBlank
    private String uri;
}
