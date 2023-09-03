package com.tiger.pocs.repository.graph;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.elementMap;

@Component
@Qualifier("GraphSampleRepository")
public class GraphSampleRepository {

    Logger logger = LoggerFactory.getLogger(GraphSampleRepository.class);
    private final GraphTraversalSource g;
    private final Gson customGson;

    public GraphSampleRepository(GraphTraversalSource g, Gson customGson) {
        this.g = g;
        this.customGson = customGson;
    }

    /**
     * @param object : Entity to be persisted
     * @param label  : label of entity (Prefix of entityName)
     * @return Object : The returned created object
     */
    public Object createVertex(Object object, String label) {

        try {
            Map<String, Object> map = getObjectMap(object);
            GraphTraversal<Vertex, Vertex> traversal = g.addV(label);
            map.keySet().forEach(key -> traversal.property(key, map.get(key)));
            return traversal.coalesce(elementMap()).next();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public Set<Map<Object, Object>> findAll(String label) {
        return g.V().hasLabel(label).elementMap().toSet();
    }

    public Set<Map<Object, Object>> findByKey(String label, String key, String value) {
        return g.V().has(label, key, value).elementMap().toSet();
    }

    private Map<String, Object> getObjectMap(Object object) {
        String json = customGson.toJson(object);
        return customGson.fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType());
    }

    public Object LinkVertices(Object startV, String startVLabel, Object endV, String endVLabel, String edge, String edgeName) {

        try {
            Map<String, Object> mapStartV = getObjectMap(startV);
            Map<String, Object> mapEndV = getObjectMap(endV);
            Map<String, Object> mapEdge = getObjectMap(edge);

            GraphTraversal<Vertex, Vertex> traversalSV = g.addV(startVLabel);
            mapStartV.keySet().forEach(key -> traversalSV.property(key, mapStartV.get(key)));
            traversalSV.next();

            GraphTraversal<Vertex, Vertex> traversalEV = g.addV(endVLabel);
            mapEndV.keySet().forEach(key -> traversalEV.property(key, mapStartV.get(key)));
            traversalEV.next();

            GraphTraversal<Edge, Edge> traversalEdge = g.addE(edgeName).from(traversalSV).to(traversalEV);
            mapEdge.keySet().forEach(key -> traversalEdge.property(key, mapStartV.get(key)));
            traversalEdge.next();

            return g.V().where(traversalSV).as("vert1").both(edgeName).coalesce(elementMap());
        } catch (Exception e) {
            logger.error("**** {}", e.getMessage());
        }
        return null;
    }


}
