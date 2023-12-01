package com.tiger.pocs.domain.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@XmlSeeAlso({RegisteredAsset.class, RealEstateAsset.class})
public abstract class Asset implements IAsset {
    @XmlElement
    Integer id;

    static class Adapter extends XmlAdapter<Asset, IAsset> {
        public IAsset unmarshal(Asset v) {
            return v;
        }

        public Asset marshal(IAsset v) {
            return (Asset) v;
        }
    }
}
