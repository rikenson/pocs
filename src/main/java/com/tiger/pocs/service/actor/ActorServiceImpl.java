package com.tiger.pocs.service.actor;

import com.google.gson.Gson;
import com.tiger.pocs.payload.Actor;
import com.tiger.pocs.repository.graph.DomainRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {


    private final DomainRepository repository;
    private final Gson customGson;

    public ActorServiceImpl(DomainRepository repository, Gson customGson) {
        this.repository = repository;
        this.customGson = customGson;
    }

    @Override
    public String add(Actor request) {
        return repository.createActor(request);
    }

    @Override
    public void edit(Actor request, UUID uuid) {

    }

    @Override
    public Actor retrieve(String key, String value) {
        return repository.findByKey(key, value);
    }

    @Override
    public Set<Actor> retrieveAll() {
        return repository.findAll();
    }

    @Override
    public Set<Actor> retrieveByCriteria(Object filter) {
        return null;
    }

    @Override
    public void remove(UUID uuid) {

    }
}
