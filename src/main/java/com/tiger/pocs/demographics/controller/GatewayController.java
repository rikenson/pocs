package com.tmt.demographics.controller;

import com.tmt.demographics.entity.ApiRoute;
import com.tmt.demographics.request.ApiRouteRequest;
import com.tmt.demographics.service.ApiRouteService;
import com.tmt.demographics.utils.Constant;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(Constant.V1_API_ROUTES)
@CrossOrigin
public class GatewayController {

    private final ApiRouteService apiRouteService;

    public GatewayController(ApiRouteService apiRouteService) {
        this.apiRouteService = apiRouteService;
    }

    @GetMapping
    public Flux<ApiRoute> getApiRoutes() {
        return apiRouteService.findApiRoutes();
    }

    @GetMapping("/{currentId}")
    public Mono<ApiRoute> getApiRouteById(@PathVariable UUID currentId) {
        return apiRouteService.getApiRoute(currentId);
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiRoute> createApiRoute(@RequestBody ApiRouteRequest request) {
        return apiRouteService.addApiRoute(request);
    }

    @PutMapping("/{currentId}")
    public Mono<Void> updateApiRoute(@PathVariable UUID currentId, @RequestBody ApiRouteRequest request) {
        return apiRouteService.editApiRoute(currentId, request);
    }

    @DeleteMapping("/{currentId}")
    public Mono<Boolean> updateApiRoute(@PathVariable UUID currentId) {
        return apiRouteService.deleteApiRoute(currentId);
    }
}
