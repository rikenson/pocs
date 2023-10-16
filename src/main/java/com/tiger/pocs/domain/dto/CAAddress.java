package com.tiger.pocs.domain.dto;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Builder;

@Builder
public class CAAddress extends Address {
    @XmlElement
    String province = "QC";
    @XmlElement
    String geoPosition = "Ouest";
}
