package com.tiger.pocs.domain.dto;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Builder;


@Builder
public class Beneficiary extends Customer {
    @XmlElement
    String link = "sister";
    @XmlElement
    IAddress address;
}
