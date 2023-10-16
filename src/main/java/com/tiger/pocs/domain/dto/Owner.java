package com.tiger.pocs.domain.dto;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Builder;

@Builder
public class Owner extends Customer {
    @XmlElement
    String type = "principal";
    @XmlElement
    IAddress address;
}
