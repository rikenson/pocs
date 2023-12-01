package com.tiger.pocs.service.generator.xml.validator;

public interface IXmlValidator {
    Boolean isXsdValid(String xsdPath, String xmlPath);

    <T> Boolean isContentValid(T entity);
}
