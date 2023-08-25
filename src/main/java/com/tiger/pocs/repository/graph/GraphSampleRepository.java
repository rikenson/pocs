package com.tiger.pocs.repository.graph;

import com.google.gson.Gson;
import com.tiger.pocs.payload.Sample;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.*;

@Component
@Qualifier("GraphSampleRepository")
public class GraphSampleRepository {

    private final GraphTraversalSource g;
    private final Gson customGson;
    private final String label = Sample.class.getSimpleName().toLowerCase();

    public GraphSampleRepository(GraphTraversalSource g, Gson customGson) {
        this.g = g;
        this.customGson = customGson;
    }

    public String create(Sample model) {
        Vertex newVertex = g.addV(label)
                .property("uuid", UUID.randomUUID().toString())
                .property("name", model.getName())
                .property("description", model.getDescription())
                .property("created_at", LocalDateTime.now())
                .property("updated_at", LocalDateTime.now())
                .next();
        return newVertex.toString();
    }


    public Set<Sample> findAll() {
        var data = g.V().has(label, "uuid", "50959e09-5177-452d-8bf0-0898cc8fde90")
                .in("acts_as").as("sample")
                .out("is_a").toSet();

        return data.stream()
                .map(o -> customGson.fromJson(customGson.toJson(o), Sample.class))
                .collect(Collectors.toSet());
    }

    public Sample findByKey(String key, String value) {
        var found = g.V().has(Sample.class.getSimpleName().toLowerCase(), key, value)
                .as("person")
                .select("person")
                    .by(project("uuid", "name", "description")
                        .by("uuid")
                        .by("name")
                        .by("description")
                )
                .next();
        return customGson.fromJson(customGson.toJson(found), Sample.class);
    }


}
