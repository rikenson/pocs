package com.tiger.pocs.service.generator.core;


public interface FileGenerator {

    <T> String generateXml(T model, String regulatory, Boolean isError);

    <T> String generateCsv(T model, String lobName, Boolean isError);

}
