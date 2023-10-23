package com.tiger.pocs.domain.dto;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Builder;

@Builder
public class USAddress extends Address {
    @XmlElement
    String zipCode = "19078";
    @XmlElement
    String state = "Ohio";
}
