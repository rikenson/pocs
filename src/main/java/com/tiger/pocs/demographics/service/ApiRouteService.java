package com.tmt.demographics.service;

import com.tmt.demographics.entity.ApiRoute;
import com.tmt.demographics.request.ApiRouteRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ApiRouteService {
    Mono<ApiRoute> getApiRoute(UUID currentId);

    Mono<ApiRoute> addApiRoute(ApiRouteRequest model);

    Mono<Void> editApiRoute(UUID currentId, ApiRouteRequest model);

    Mono<Boolean> deleteApiRoute(UUID currentI);

    Flux<ApiRoute> findApiRoutes();
}
