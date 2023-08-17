package com.tmt.demographics.service;

import com.tmt.demographics.entity.ApiRoute;
import com.tmt.demographics.request.ApiRouteRequest;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ApiRouteServiceImpl implements ApiRouteService {

    private final ReactiveMongoOperations reactiveMongoOperations;
    private final GatewayRouteService gatewayRouteService;

    public ApiRouteServiceImpl(ReactiveMongoOperations reactiveMongoOperations, GatewayRouteService gatewayRouteService) {
        this.reactiveMongoOperations = reactiveMongoOperations;
        this.gatewayRouteService = gatewayRouteService;
    }

    @Override
    public Mono<ApiRoute> getApiRoute(UUID currentId) {
        return reactiveMongoOperations.findById(currentId, ApiRoute.class);
    }

    @Override
    public Mono<ApiRoute> addApiRoute(ApiRouteRequest apiRouteRequest) {
        ApiRoute route = setNewApiRoute(new ApiRoute(), apiRouteRequest);
        return reactiveMongoOperations.save(route)
                .doOnSuccess(apiRoute -> gatewayRouteService.refreshRoutes());
    }

    @Override
    public Mono<Void> editApiRoute(UUID currentId, ApiRouteRequest apiRouteRequest) {

        return findAndValidateApiRoute(currentId)
                .map(apiRoute -> setNewApiRoute(apiRoute, apiRouteRequest))
                .flatMap(reactiveMongoOperations::save)
                .doOnSuccess(obj -> gatewayRouteService.refreshRoutes())
                .then();
    }

    @Override
    public Mono<Boolean> deleteApiRoute(UUID currentId) {
        return reactiveMongoOperations.remove(
                        Query.query(Criteria.where("id").is(currentId)), ApiRoute.class)
                .flatMap(deleteResult -> Mono.just(deleteResult.wasAcknowledged()))
                .doOnSuccess(apiRoute -> gatewayRouteService.refreshRoutes());
    }

    @Override
    public Flux<ApiRoute> findApiRoutes() {
        return reactiveMongoOperations.findAll(ApiRoute.class);
    }

    private Mono<ApiRoute> findAndValidateApiRoute(UUID currentId) {
        return reactiveMongoOperations.findById(currentId, ApiRoute.class)
                .switchIfEmpty(Mono.error(
                        new RuntimeException(String.format("Api route with id %d not found", 1))));
    }

    private ApiRoute setNewApiRoute(ApiRoute apiRoute, ApiRouteRequest apiRouteRequest) {
        apiRoute.setPath(apiRouteRequest.getPath());
        apiRoute.setVerb(apiRouteRequest.getVerb());
        apiRoute.setUri(apiRouteRequest.getUri());
        return apiRoute;
    }
}
