package com.tiger.pocs.domain.dto;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(Customer.Adapter.class)
public interface ICustomer {
}
