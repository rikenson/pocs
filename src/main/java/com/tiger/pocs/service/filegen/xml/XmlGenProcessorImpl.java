package com.tiger.pocs.service.filegen.xml;

import com.tiger.pocs.domain.dto.*;
import com.tiger.pocs.service.filegen.core.FileGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XmlGenProcessorImpl implements XmlGenProcessor {
    private final FileGenerator fileGenerator;
    Logger logger = LoggerFactory.getLogger(XmlGenProcessorImpl.class);

    public XmlGenProcessorImpl(FileGenerator fileGenerator) {
        this.fileGenerator = fileGenerator;
    }

    @Override
    public void processBasicReport() {
        var model = Remise.builder()
                .realEstates(RealEstateAsset.builder()
                        .beneficiaries(List.of(
                                Beneficiary.builder().address(USAddress.builder().state("BST").build()).build(),
                                Beneficiary.builder().address(CAAddress.builder().province("QC").build()).build()
                        ))
                        .owners(List.of(
                                Owner.builder().address(CAAddress.builder().geoPosition("EST").build()).build(),
                                Owner.builder().address(USAddress.builder().zipCode("US-89").build()).build()
                        )).build()
                )
                .registered(RegisteredAsset.builder()
                        .owners(List.of(
                                Owner.builder().address(CAAddress.builder().province("ALBERTA").build()).build(),
                                Owner.builder().address(USAddress.builder().state("NY").build()).build()
                        )).build()
                )
                .build();
        String reportFilename = fileGenerator.generateXml(model, "PL17", Boolean.FALSE);
        logger.info("file generated: {}", reportFilename);
    }


}
