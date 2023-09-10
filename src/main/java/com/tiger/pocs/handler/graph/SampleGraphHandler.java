package com.tiger.pocs.handler.graph;

import com.tiger.pocs.payload.*;
import com.tiger.pocs.service.sample.graph.SampleGraphService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/graph/samples")
public class SampleGraphHandler {

    private final SampleGraphService service;

    public SampleGraphHandler(SampleGraphService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SampleResponse> samplePost(@RequestBody @Validated SampleRequest request) {
        return new ResponseEntity<>(service.add(request), HttpStatus.CREATED);
    }

    @PatchMapping("{uuid}")
    public ResponseEntity<SampleResponse> partialSampleEdit(
            @RequestBody @Validated PatchSampleRequest request,
            @PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(service.edit(request, uuid));
    }

    @GetMapping("{uuid}")
    public ResponseEntity<SampleResponse> sampleRetrieve(@PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(service.retrieve(uuid));
    }

    @GetMapping
    public ResponseEntity<List<SampleResponse>> sampleRetrieveAll() {
        return ResponseEntity.ok(service.retrieveAll());
    }

    @GetMapping("/by-key-value")
    public ResponseEntity<List<SampleResponse>> sampleRetrieveByFilter(
            @RequestParam("key") String key,
            @RequestParam("value") String value) {
        return ResponseEntity.ok(service.retrieveByKeyValue(key, value));
    }

    @PostMapping("/link-vertices")
    public ResponseEntity<Object> linkExistingVertices(
            @RequestBody @Validated SampleEdge edge,
            @RequestParam("eLabel") String eLabel,
            @RequestParam("sVLabel") String sVLabel,
            @RequestParam("sVId") String sVId,
            @RequestParam("eVLabel") String eVLabel,
            @RequestParam("eVId") String eVId
    ) {
        return ResponseEntity.ok(service.linkExistingVertices(edge, eLabel, sVLabel, sVId, eVLabel, eVId));
    }

    @DeleteMapping
    public ResponseEntity<Long> remove(@RequestParam("key") String key, @RequestParam("value") String value) {
        return ResponseEntity.ok(service.remove(key, value));
    }


    @PostMapping("/link")
    public ResponseEntity<Object> linkVertices(
            @RequestBody LinkedRequest request,
            @RequestParam("sCriteriaKey") String sCriteriaKey,
            @RequestParam("sCriteriaValue") String sCriteriaValue,
            @RequestParam("eCriteriaKey") String eCriteriaKey,
            @RequestParam("eCriteriaValue") String eCriteriaValue
    ) {
        return ResponseEntity.ok(service.linkVertices(
                request.getSampleEdge(), request.getLinkLabel(),
                request.geteVertex(), sCriteriaKey, sCriteriaValue,
                request.getsVertex(), eCriteriaKey, eCriteriaValue
        ));
    }


}
