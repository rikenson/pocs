package com.tiger.pocs.domain.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Builder;

@Builder
@XmlType(name = "USAddress")
public class USAddress extends Address {
    @XmlElement
    String zipCode;
    @XmlElement
    String state;
}
