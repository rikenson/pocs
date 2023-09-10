package com.tiger.pocs.repository.graph;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.validation.constraints.NotNull;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.tiger.pocs.utils.FixedValues.CREATED_AT_FIELD;
import static com.tiger.pocs.utils.FixedValues.MODIFIED_AT_FIELD;
import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.*;

@Component
@Qualifier("GraphSampleRepository")
public class GremlinProxy {

    Logger logger = LoggerFactory.getLogger(GremlinProxy.class);
    private final GraphTraversalSource g;
    private final Gson customGson;

    public GremlinProxy(GraphTraversalSource g, Gson customGson) {
        this.g = g;
        this.customGson = customGson;
    }


    /**
     * @param object : Entity to be persisted
     * @param label  : label of entity (Prefix of entityName)
     * @return Object : The returned created object
     */
    public Object save(@NotNull final Object object, @NotNull final String label) {
        GraphTraversal<Vertex, Vertex> traversal = g.addV(label);
        try {
            Map<String, Object> map = getObjectMap(object);
            map.keySet().forEach(key -> traversal.property(key, map.get(key)));
            traversal.property(CREATED_AT_FIELD, LocalDateTime.now().toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return traversal.coalesce(elementMap()).next();
    }


    /**
     * @param object         : Entity to be saved
     * @param label          : Label of entity
     * @param criteriaKey    : key by which vertex must be search
     * @param criteriaValue: value of key by which vertex must be search
     * @return Object : Return the Object that was created
     */
    public Object update(@NotNull final Object object,
                         @NotNull final String label,
                         @NotNull final String criteriaKey,
                         @NotNull final String criteriaValue) {
        GraphTraversal<Vertex, Vertex> traversal = g.V().has(label, criteriaKey, criteriaValue);
        try {
            Map<String, Object> map = getObjectMap(object);
            map.keySet().forEach(key -> traversal.property(key, map.get(key)));
            traversal.property(MODIFIED_AT_FIELD, LocalDateTime.now().toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return traversal.coalesce(elementMap()).next();
    }


    /**
     * @param label          : Label of the vertex that must be deleted
     * @param criteriaKey    : key by which vertex must be search
     * @param criteriaValue: value of key by which vertex must be search
     * @return Long: Return the count of vertices removed
     */
    public Long delete(@NotNull final String label,
                       @NotNull final String criteriaKey,
                       @NotNull final String criteriaValue) {
        return g.V().has(label, criteriaKey, criteriaValue).
                sideEffect(__.drop()).
                count().
                next();
    }


    /**
     * @param label : Label of the vertices that must be found
     * @return Set<Map < Object, Object>> : Return the set of all vertices found for that label
     */
    public Set<Map<Object, Object>> findAll(@NotNull final String label) {
        return g.V().hasLabel(label).elementMap().toSet();
    }


    /**
     * @param label          : Label of the vertex that must be deleted
     * @param criteriaKey    : key by which vertex must be search
     * @param criteriaValue: value of key by which vertex must be search
     * @return Set<Map < Object, Object>>:  Return the list of vertices retrieved for the label and key, value
     */
    public Set<Map<Object, Object>> findByKey(@NotNull final String label,
                                              @NotNull final String criteriaKey,
                                              @NotNull final String criteriaValue) {
        return g.V().has(label, criteriaKey, criteriaValue).elementMap().toSet();
    }


    /**
     * @param edge          : Edge Entity between vertices
     * @param edgeLabel     : Edge label
     * @param sVertexLabel  : Starting vertex label
     * @param sVertexId     : starting vertex id
     * @param eVertexLabel: Ending vertex label
     * @param eVertexId:    Ending vertex id
     * @return Object : The linked objects in json structure
     */
    public Object linkExistingVertices(@NotNull final Object edge,
                                       @NotNull final String edgeLabel,
                                       @NotNull final String sVertexLabel,
                                       @NotNull final String sVertexId,
                                       @NotNull final String eVertexLabel,
                                       @NotNull final String eVertexId) {

        GraphTraversal<Edge, Edge> traversal = g.addE(edgeLabel);
        traversal.property(CREATED_AT_FIELD, LocalDateTime.now().toString());
        try {
            Map<String, Object> map = getObjectMap(edge);
            map.keySet().forEach(key -> traversal.property(key, map.get(key)));
            traversal.from(__.V().has(sVertexLabel, "uuid", sVertexId))
                    .to(__.V().has(eVertexLabel, "uuid", eVertexId));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return traversal.coalesce(elementMap()).next();
    }


    /**
     * @param edge             :
     * @param edgeLabel:
     * @param sVertex:
     * @param sVLabel:
     * @param sVCriteriaKey:
     * @param sVCriteriaValue:
     * @param eVertex:
     * @param eVLabel:
     * @param eVCriteriaKey:
     * @param eVCriteriaValue:
     * @return Object
     */
    public Object linkVertices(@NotNull final Object edge,
                               @NotNull final String edgeLabel,
                               @NotNull final Object sVertex,
                               @NotNull final String sVLabel,
                               @NotNull final String sVCriteriaKey,
                               @NotNull final String sVCriteriaValue,
                               @NotNull final Object eVertex,
                               @NotNull final String eVLabel,
                               @NotNull final String eVCriteriaKey,
                               @NotNull final String eVCriteriaValue
    ) {
        GraphTraversal<Vertex, List<Vertex>> traversal = g.V().has(sVLabel, sVCriteriaKey, sVCriteriaValue).fold();

        try {
            Map<String, Object> mapEdge = getObjectMap(edge);
            Map<String, Object> mapSVertex = getObjectMap(sVertex);
            Map<String, Object> mapEVertex = getObjectMap(eVertex);

            var newSVertex = addV(sVLabel);
            newSVertex.property(CREATED_AT_FIELD, LocalDateTime.now().toString());
            mapSVertex.keySet().forEach(key -> newSVertex.property(key, mapSVertex.get(key)));

            var newEVertex = addV(eVLabel);
            newEVertex.property(CREATED_AT_FIELD, LocalDateTime.now().toString());
            mapEVertex.keySet().forEach(key -> newEVertex.property(key, mapEVertex.get(key)));

            var newEdge = addE(edgeLabel);
            newEdge.property(CREATED_AT_FIELD, LocalDateTime.now().toString());
            mapEdge.keySet().forEach(key -> newEdge.property(key, mapEdge.get(key)));

            traversal
                    .coalesce(unfold(), newSVertex)
                    .as("sV")
                    .coalesce(has(eVLabel, eVCriteriaKey, eVCriteriaValue).fold().coalesce(unfold(), newEVertex))
                    .as("eV")
                    .coalesce(newEdge.from(select("sV")).to(select("eV")));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return traversal.coalesce(elementMap()).next();
    }

    public Object executeTraversal(@NotNull final String traversal) {
        //TODO Implements the method to execute subgraph traversal
        return traversal;
    }


    private Map<String, Object> getObjectMap(Object object) {
        String json = customGson.toJson(object);
        return customGson.fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType());
    }

}
