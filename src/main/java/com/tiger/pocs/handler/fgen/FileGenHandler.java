package com.tiger.pocs.handler.fgen;

import com.tiger.pocs.service.filegen.xml.XmlGenProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/file")
public class FileGenHandler {

    private final XmlGenProcessor xmlGenProcessor;

    public FileGenHandler(XmlGenProcessor xmlGenProcessor) {
        this.xmlGenProcessor = xmlGenProcessor;
    }


    @GetMapping("/generate")
    public ResponseEntity<Void> retrieveParam() {
        xmlGenProcessor.processBasicReport();
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
