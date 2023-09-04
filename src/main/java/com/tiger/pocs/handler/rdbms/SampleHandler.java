package com.tiger.pocs.handler.rdbms;

import com.tiger.pocs.domain.enums.Status;
import com.tiger.pocs.domain.filter.SampleFilter;
import com.tiger.pocs.payload.SampleRequest;
import com.tiger.pocs.payload.SampleResponse;
import com.tiger.pocs.service.sample.rdbms.SampleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping("/samples")
public class SampleHandler {

    private final SampleService service;

    public SampleHandler(SampleService service) {
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

    @GetMapping("/by-filter")
    public ResponseEntity<List<SampleResponse>> sampleRetrieveByFilter(
            @RequestParam("name") String name,
            @RequestParam("status") String status,
            @RequestParam("startDateTime") LocalDateTime startDateTime,
            @RequestParam("endDateTime") LocalDateTime endDateTime,
            @RequestParam("preferredField") String preferredField) {
        SampleFilter filter = SampleFilter
                .builder()
                .name(name)
                .status(Status.valueOf(status))
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .preferredField(preferredField)
                .build();
        return ResponseEntity.ok(service.retrieveByCriteria(filter));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<Void> remove(@PathVariable("uuid") String uuid) {
        service.remove(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
