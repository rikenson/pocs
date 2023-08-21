package com.tiger.pocs.service.actor;

import com.tiger.pocs.payload.Actor;

import java.util.Set;
import java.util.UUID;

public interface ActorService {

    String add(Actor request);

    void edit(Actor request, UUID uuid);

    Actor retrieve(String key, String value);

    Set<Actor> retrieveAll();

    Set<Actor> retrieveByCriteria(Object filter);

    void remove(UUID uuid);
}
