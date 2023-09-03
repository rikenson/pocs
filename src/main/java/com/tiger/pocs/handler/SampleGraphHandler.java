package com.tiger.pocs.handler;

import com.tiger.pocs.payload.SampleRequest;
import com.tiger.pocs.payload.SampleResponse;
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

    @PutMapping("{uuid}")
    public ResponseEntity<SampleResponse> sampleEdit(
            @RequestBody @Validated SampleResponse request,
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

    @DeleteMapping("{uuid}")
    public ResponseEntity<Void> remove(@PathVariable("uuid") String uuid) {
        service.remove(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
