package com.tiger.pocs.domain.dto;

import com.tiger.pocs.adapter.LocalDateTimeAdapter;
import com.tiger.pocs.payload.SampleResponse;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement(name = "report")
@XmlAccessorType(XmlAccessType.FIELD)
public class SampleReport {

    @XmlAttribute
    private String description;

    @XmlAttribute
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime generatedAt;

    @XmlElement(name = "sample")
    private List<SampleResponse> responses;
}
