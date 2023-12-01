package com.tiger.pocs.service.generator.core;

import com.tiger.pocs.domain.dto.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.util.function.BiFunction;

@Service
public class FileGeneratorImpl implements FileGenerator {
    private final Logger logger = LoggerFactory.getLogger(FileGeneratorImpl.class);
    BiFunction<String, Boolean, String> buildFilename = (prefix, isError) ->
            String.format("%s-%s%s.xml", prefix, LocalDate.now(), Boolean.TRUE.equals(isError) ? "-ERROR" : "");

    @Override
    public <T> String generateXml(T model, String regulatory, Boolean isError) {
        var filename = buildFilename.apply(regulatory, isError);
        try {
            JAXBContext context = JAXBContext.newInstance(
                    model.getClass(), RealEstateAsset.class, RegisteredAsset.class,
                    Beneficiary.class, Owner.class, CAAddress.class, USAddress.class
            );
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "");
            mar.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.mysite.com/abc.xsd");
            mar.marshal(model, new File(filename));
        } catch (JAXBException e) {
            logger.error("Could not generate file because of: {}, {}", e, e.getMessage());
        }

        return filename;
    }

    @Override
    public <T> String generateCsv(T model, String lobName, Boolean isError) {
        return null;
    }

}
