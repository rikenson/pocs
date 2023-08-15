package com.tiger.pocs.service.domain;

import com.tiger.pocs.payload.Domain;
import com.tiger.pocs.repository.graph.DomainRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DomainServiceImpl implements DomainService {

    private final DomainRepository repository;

    public DomainServiceImpl(DomainRepository repository) {
        this.repository = repository;
    }

    @Override
    public String add(Domain request) {
        return repository.create(request);
    }

    @Override
    public Domain edit(Domain request, UUID uuid) {
        return null;
    }

    @Override
    public Domain retrieve(UUID uuid) {
        return null;
    }

    @Override
    public List<Domain> retrieveAll() {
        return null;
    }

    @Override
    public List<Domain> retrieveByCriteria(Object filter) {
        return null;
    }

    @Override
    public void remove(UUID uuid) {

    }
}
