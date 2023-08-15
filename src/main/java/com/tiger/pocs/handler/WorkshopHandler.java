package com.tiger.pocs.handler;

import com.tiger.pocs.payload.*;
import com.tiger.pocs.service.WorkshopService;
import com.tiger.pocs.service.domain.DomainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Controller
public class WorkshopHandler {

    private final WorkshopService service;
    private final DomainService domainService;

    public WorkshopHandler(WorkshopService service, DomainService domainService) {
        this.service = service;
        this.domainService = domainService;
    }

    @PostMapping
    public ResponseEntity<WorkshopResponse> workshopPost(@RequestBody @Validated WorkshopRequest request) {
        return new ResponseEntity<>(service.add(request), HttpStatus.CREATED);
    }
    @PostMapping("/domains")
    public ResponseEntity<String> domainPost(@RequestBody Domain request) {
        return new ResponseEntity<>(domainService.add(request), HttpStatus.CREATED);
    }

    @PutMapping("{uuid}")
    public ResponseEntity<WorkshopResponse> workshopEdit(
            @RequestBody @Validated UpdatedWorkshopRequest request,
            @PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(service.edit(request, uuid));
    }
    @PatchMapping("{uuid}")
    public ResponseEntity<WorkshopResponse> addParticipants(
            @RequestBody @Validated List<ParticipantRequest> request,
            @PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(service.addParticipants(request, uuid));
    }

    @GetMapping("{uuid}")
    public ResponseEntity<WorkshopResponse> workshopRetrieve(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(service.retrieve(uuid));
    }

    @GetMapping
    public ResponseEntity<List<WorkshopResponse>> workshopRetrieveAll() {
        return ResponseEntity.ok(service.retrieveAll());
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<Void> remove(@PathVariable("uuid") UUID uuid) {
        service.remove(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
