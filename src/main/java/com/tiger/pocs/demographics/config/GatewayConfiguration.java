package com.tmt.demographics.config;

import com.tmt.demographics.service.ApiPathRouteLocatorImpl;
import com.tmt.demographics.service.ApiRouteService;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator routeLocator(ApiRouteService apiRouteService,
                                     RouteLocatorBuilder routeLocatorBuilder) {
        return new ApiPathRouteLocatorImpl(apiRouteService, routeLocatorBuilder);
    }
}
