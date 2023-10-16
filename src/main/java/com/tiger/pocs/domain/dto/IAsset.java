package com.tiger.pocs.domain.dto;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(Asset.Adapter.class)
public interface IAsset {
}
