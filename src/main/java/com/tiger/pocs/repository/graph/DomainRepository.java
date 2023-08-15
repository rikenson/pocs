package com.tiger.pocs.repository.graph;

import com.tiger.pocs.payload.Domain;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class DomainRepository {

    private final GraphTraversalSource g;
    private final String label = Domain.class.getSimpleName().toLowerCase();

    public DomainRepository(GraphTraversalSource g) {
        this.g = g;
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


}
