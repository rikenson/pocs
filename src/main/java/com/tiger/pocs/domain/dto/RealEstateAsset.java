package com.tiger.pocs.domain.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.Builder;

import java.util.List;

@Builder
public class RealEstateAsset extends Asset {
    @XmlElement
    String name = "peter";
    @XmlElement
    String accountNumber = "12345";

    @XmlElementWrapper(name = "beneficiaries")
    @XmlElement(name = "beneficiary", type = Beneficiary.class)
    List<ICustomer> beneficiaries;

    @XmlElementWrapper(name = "owners")
    @XmlElement(name = "owner", type = Owner.class)
    List<ICustomer> owners;

}
