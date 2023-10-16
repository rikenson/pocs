package com.tiger.pocs.domain.dto;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(Address.Adapter.class)
public interface IAddress {
}
