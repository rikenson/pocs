package com.tmt.demographics.service;

import com.tmt.demographics.entity.ApiRoute;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.*;
import org.springframework.cloud.gateway.route.builder.*;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

@AllArgsConstructor
public class ApiPathRouteLocatorImpl implements RouteLocator {
    private final ApiRouteService apiRouteService;
    private final RouteLocatorBuilder routeLocatorBuilder;

    @Override
    public Flux<Route> getRoutes() {
        RouteLocatorBuilder.Builder routesBuilder = routeLocatorBuilder.routes();
        return apiRouteService.findApiRoutes()
                .map(apiRoute -> routesBuilder.route(String.valueOf(apiRoute.getCurrentId()),
                        predicateSpec -> setPredicateSpec(apiRoute, predicateSpec)))
                .collectList()
                .flatMapMany(builders -> routesBuilder.build()
                        .getRoutes());
    }

    private Buildable<Route> setPredicateSpec(ApiRoute apiRoute, PredicateSpec predicateSpec) {
        BooleanSpec booleanSpec = predicateSpec.path(apiRoute.getPath());
        if (!StringUtils.isEmpty(apiRoute.getVerb())) {
            booleanSpec.and().method(apiRoute.getVerb().name());
        }
        return booleanSpec.uri(apiRoute.getUri());
    }
}
