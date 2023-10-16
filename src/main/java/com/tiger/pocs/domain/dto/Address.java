package com.tiger.pocs.domain.dto;

import jakarta.persistence.MappedSuperclass;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

@MappedSuperclass
public abstract class Address implements IAddress {
    @XmlElement
    String civicNo = "491";
    @XmlElement
    String apartNo = "B";
    @XmlElement
    String streetName = "Leclerc";

    static class Adapter extends XmlAdapter<Address, IAddress> {
        public IAddress unmarshal(Address v) {
            return v;
        }

        public Address marshal(IAddress v) {
            return (Address) v;
        }
    }
}
