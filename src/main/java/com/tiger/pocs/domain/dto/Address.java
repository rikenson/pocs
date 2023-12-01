package com.tiger.pocs.domain.dto;

import jakarta.persistence.MappedSuperclass;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

@MappedSuperclass
@XmlSeeAlso({USAddress.class, CAAddress.class})
public abstract class Address implements IAddress {
    @XmlElement
    String civicNo;
    @XmlElement
    String apartNo;
    @XmlElement
    String streetName;

    static class Adapter extends XmlAdapter<Address, IAddress> {
        public IAddress unmarshal(Address v) {
            return v;
        }

        public Address marshal(IAddress v) {
            return (Address) v;
        }
    }
}
