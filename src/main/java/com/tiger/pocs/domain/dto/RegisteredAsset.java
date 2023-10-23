package com.tiger.pocs.domain.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.Builder;

import java.util.List;

@Builder
public class RegisteredAsset extends Asset {
    @XmlElement
    Integer counter = 23;
    @XmlElement
    String accountType = "Voilà";

    @XmlElementWrapper(name = "owners")
    @XmlElement(name = "owner", type = Owner.class)
    List<ICustomer> owners;
}
