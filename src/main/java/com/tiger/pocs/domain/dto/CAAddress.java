package com.tiger.pocs.domain.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Builder;

@Builder
@XmlType(name = "CAAddress")
public class CAAddress extends Address {
    @XmlElement
    String province;
    @XmlElement
    String geoPosition;
}
