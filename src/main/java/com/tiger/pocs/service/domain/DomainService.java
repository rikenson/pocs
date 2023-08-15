package com.tiger.pocs.service.domain;

import com.tiger.pocs.payload.Domain;

import java.util.List;
import java.util.UUID;

public interface DomainService {

    String add(Domain request);

    Domain edit(Domain request, UUID uuid);

    Domain retrieve(UUID uuid);

    List<Domain> retrieveAll();

    List<Domain> retrieveByCriteria(Object filter);

    void remove(UUID uuid);
}
