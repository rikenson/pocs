package com.tiger.pocs.domain.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Builder;

import java.util.List;

@Builder
@XmlType(name = "RegisteredAsset")
public class RegisteredAsset extends Asset {
    @XmlElement
    Integer counter;
    @XmlElement
    String accountType;

    @XmlElementWrapper(name = "owners")
    @XmlElement(name = "owner", type = Owner.class)
    List<? extends ICustomer> owners;
}
