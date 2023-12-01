package com.tiger.pocs.domain.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Builder;


@Builder
@XmlType(name = "Beneficiary")
public class Beneficiary extends Customer {
    @XmlElement
    String link = "sister";
    @XmlElement
    IAddress address;
}
