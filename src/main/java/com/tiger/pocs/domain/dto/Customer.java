package com.tiger.pocs.domain.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
public abstract class Customer implements ICustomer {

    @XmlElement
    String firstname = "Rikenson";
    @XmlElement
    String lastname = "JACQUES";

    static class Adapter extends XmlAdapter<Customer, ICustomer> {
        public ICustomer unmarshal(Customer v) {
            return v;
        }

        public Customer marshal(ICustomer v) {
            return (Customer) v;
        }
    }
}
