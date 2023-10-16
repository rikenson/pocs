package com.tiger.pocs.validator;

import lombok.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class XmlValidator {
    Function<String, File> getFile = location ->
            new File(Objects.requireNonNull(getClass().getClassLoader().getResource(location)).getFile());
    private String xsdFilename;
    private String xmlFilename;

    private Validator initValidator(String xsdPath) throws SAXException {
        SchemaFactory factory = SchemaFactory.newDefaultInstance();
        factory.setFeature(XMLConstants.W3C_XML_SCHEMA_NS_URI, Boolean.TRUE);
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, Boolean.TRUE);
        Source schemaFile = new StreamSource(getFile.apply(xsdPath));
        Schema schema = factory.newSchema(schemaFile);
        return schema.newValidator();
    }

    public boolean isValid(String xsdPath, String xmlPath) throws IOException, SAXException {
        Validator validator = initValidator(xsdPath);
        try {
            validator.validate(new StreamSource(getFile.apply(xmlPath)));
            return true;
        } catch (SAXException e) {
            return false;
        }
    }

}
