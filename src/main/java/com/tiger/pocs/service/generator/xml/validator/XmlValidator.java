package com.tiger.pocs.service.generator.xml.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator implements IXmlValidator {
    @Value("classpath:data/resource-data.txt")
    Resource resourceFile;
    Logger logger = LoggerFactory.getLogger(XmlValidator.class);


    @Override
    public Boolean isXsdValid(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            logger.info("Exception: {}", e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public <T> Boolean isContentValid(T entity) {
        return null;
    }
}
