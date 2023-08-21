package com.tiger.pocs.repository.graph;

import com.google.gson.Gson;
import com.tiger.pocs.payload.Actor;
import com.tiger.pocs.payload.Domain;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.tinkerpop.gremlin.process.traversal.P.within;
import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.*;

@Component
public class DomainRepository {

    private final GraphTraversalSource g;
    private final Gson customGson;
    private final String label = Domain.class.getSimpleName().toLowerCase();

    public DomainRepository(GraphTraversalSource g, Gson customGson) {
        this.g = g;
        this.customGson = customGson;
    }

    public String create(Domain model) {
        Vertex newVertex = g.addV(label)
                .property("uuid", UUID.randomUUID().toString())
                .property("code", model.getCode())
                .property("name", model.getName())
                .property("created_at", LocalDateTime.now())
                .property("updated_at", LocalDateTime.now())
                .next();
        return newVertex.toString();
    }

    public String createActor(Actor model) {
        Vertex newVertex = g.addV(Actor.class.getSimpleName().toLowerCase()).
                property("main_id", UUID.randomUUID().toString()).
                property("first_name", model.getFirstName()).
                property("last_name", model.getFirstName()).
                property("birth_date", model.getBirthDate()).
                property("birth_place", model.getBirthPlace()).
                property("gender", model.getGender()).
                next();
        return newVertex.toString();
    }

    public Set<Actor> findAll() {
        var data = g.V().has("event", "main_id", "50959e09-5177-452d-8bf0-0898cc8fde90")
                .in("acts_as").as("current_actor")
                .out("is_a")
                .where(values("name").is(within("Speaker", "Presenter")))
                .select("current_actor")
                .by(project("id", "firstName", "lastName", "gender", "birthDate", "birthPlace")
                        .by("main_id").by("first_name").by("last_name").by("gender")
                        .by("birth_date").by("birth_place")
                ).toSet();
        return data.stream()
                .map(o -> customGson.fromJson(customGson.toJson(o), Actor.class))
                .collect(Collectors.toSet());
    }

    public Actor findByKey(String key, String value) {
        var found = g.V().has(Actor.class.getSimpleName().toLowerCase(), key, value).as("person")
                .select("person")
                .by(project("id", "firstName", "lastName", "gender", "birthDate", "birthPlace")
                        .by("main_id")
                        .by("first_name")
                        .by("last_name")
                        .by("gender")
                        .by("birth_date")
                        .by("birth_place")
                )
                .next();
        return customGson.fromJson(customGson.toJson(found), Actor.class);
    }


}
