package com.tiger.pocs.handler;

import com.tiger.pocs.payload.ParamRequest;
import com.tiger.pocs.payload.ParamResponse;
import com.tiger.pocs.service.parameter.ParameterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/parameters")
public class ParameterHandler {

    private final ParameterService service;


    public ParameterHandler(ParameterService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> addParam(@RequestBody @Validated ParamRequest request) {
        service.add(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{by-name}")
    public ResponseEntity<ParamResponse> retrieveParam(@RequestParam("name") String name) {
        return new ResponseEntity<>(service.getParam(name), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ParamResponse>> retrieveParamsByPath(@RequestParam("path") String path) {
        return new ResponseEntity<>(service.getParamsByPath(path), HttpStatus.OK);
    }


}
