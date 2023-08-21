package com.tiger.pocs.handler;

import com.tiger.pocs.payload.Actor;
import com.tiger.pocs.payload.WorkshopRequest;
import com.tiger.pocs.payload.WorkshopResponse;
import com.tiger.pocs.service.actor.ActorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;


@Controller
@RequestMapping("/actors")
public class ActorHandler {

    private final ActorService service;

    public ActorHandler(ActorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> workshopPost(@RequestBody @Validated Actor request) {
        return new ResponseEntity<>(service.add(request), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Set<Actor>> retrieveAll() {
        return ResponseEntity.ok(service.retrieveAll());
    }

    @GetMapping("/by-key-value")
    public ResponseEntity<Actor> actorRetrieve(@RequestParam("key") String key, @RequestParam("value") String value) {
        return ResponseEntity.ok(service.retrieve(key,value));
    }
}
