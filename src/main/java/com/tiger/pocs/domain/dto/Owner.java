package com.tiger.pocs.domain.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Builder;

@Builder
@XmlType(name = "Owner")
public class Owner extends Customer {
    @XmlElement
    String type;
    @XmlElement
    IAddress address;
}
