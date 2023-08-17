package com.tmt.demographics.service;

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
@Service
public class GatewayRouteServiceImpl implements GatewayRouteService {

    private final ApplicationEventPublisher applicationEventPublisher;

    public GatewayRouteServiceImpl(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void refreshRoutes() {
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }
}
