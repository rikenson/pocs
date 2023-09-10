package com.tiger.pocs.service.sample.graph;

import com.google.gson.Gson;
import com.tiger.pocs.domain.entity.graph.SampleEntity;
import com.tiger.pocs.mapper.graph.SampleMapper;
import com.tiger.pocs.payload.PatchSampleRequest;
import com.tiger.pocs.payload.SampleEdge;
import com.tiger.pocs.payload.SampleRequest;
import com.tiger.pocs.payload.SampleResponse;
import com.tiger.pocs.repository.graph.GremlinProxy;
import com.tiger.pocs.utils.Utils;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiFunction;

@Service
@RequiredArgsConstructor
public class SampleGraphServiceImpl implements SampleGraphService {
    Logger logger = LoggerFactory.getLogger(SampleGraphServiceImpl.class);

    private final SampleMapper converter;
    private final GremlinProxy repository;
    private final Gson customGson;
    private final List<LinkedHashMap<String, String>> traversal;


    @Override
    public SampleResponse add(SampleRequest request) {
        try {
            var entity = converter.requestToSampleEntity(request);
            entity.setCreatedAt(LocalDateTime.now());
            var saved = repository.save(entity, Utils.toLabel(SampleRequest.class.getSimpleName()));
            return converter.entityToSampleResponse(customGson.fromJson(customGson.toJson(saved), SampleEntity.class));
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public SampleResponse edit(PatchSampleRequest request, String uuid) {
        var saved = repository.update(request, Utils.toLabel(SampleRequest.class.getSimpleName()), "uuid", uuid);
        return converter.entityToSampleResponse(customGson.fromJson(customGson.toJson(saved), SampleEntity.class));

    }

    @Override
    public SampleResponse retrieve(String uuid) {
        var data = repository.findByKey(Utils.toLabel(SampleRequest.class.getSimpleName()), "uuid", uuid);
        return data.stream()
                .map(o -> customGson.fromJson(customGson.toJson(o), SampleResponse.class))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public List<SampleResponse> retrieveAll() {

        var data = repository.findAll(Utils.toLabel(SampleRequest.class.getSimpleName()));
        return data.stream()
                .map(o -> customGson.fromJson(customGson.toJson(o), SampleResponse.class))
                .toList();
    }

    @Override
    public List<SampleResponse> retrieveByKeyValue(String key, String value) {
        var data = repository.findByKey(Utils.toLabel(SampleRequest.class.getSimpleName()), key, value);
        return data.stream()
                .map(o -> customGson.fromJson(customGson.toJson(o), SampleResponse.class))
                .toList();
    }

    @Override
    public Long remove(String key, String value) {
        return repository.delete(Utils.toLabel(SampleRequest.class.getSimpleName()), key, value);
    }
    BiFunction<List<LinkedHashMap<String, String>>, String, String> getQuery = (traversals, key) -> {
        var found = traversals.stream()
                .filter(a -> a.containsKey(key))
                .findFirst().orElse(null);
        return Objects.isNull(found) ? "" : found.get(key);
    };

    @Override
    public List<Object> findStartedSample() {
        Map<String, Object> params = new HashMap<>();
        params.put("status", "ENDED");
        return repository.executeTraversal(getQuery.apply(traversal, "started.sample"), params);
    }


    @Override
    public Object linkExistingVertices(@NotNull final SampleEdge edge,
                                       @NotNull final String edgeLabel,
                                       @NotNull final String sVertexLabel,
                                       @NotNull final String sVertexId,
                                       @NotNull final String eVertexLabel,
                                       @NotNull final String eVertexId) {
        return repository.linkExistingVertices(
                converter.requestToSampleEdgeEntity(edge), edgeLabel,
                Utils.toLabel(SampleRequest.class.getSimpleName()), sVertexId,
                Utils.toLabel(SampleRequest.class.getSimpleName()), eVertexId
        );
    }

    @Override
    public Object linkVertices(SampleEdge edge,
                               String edgeLabel,
                               SampleRequest sVertex,
                               String sVCriteriaKey,
                               String sVCriteriaValue,
                               SampleRequest eVertex,
                               String eVCriteriaKey,
                               String eVCriteriaValue) {

        var sEntity = converter.requestToSampleEntity(sVertex);
        var eEntity = converter.requestToSampleEntity(eVertex);

        return repository.linkVertices(
                edge, edgeLabel,
                sEntity, Utils.toLabel(sEntity.getClass().getSimpleName()), sVCriteriaKey, sVCriteriaValue,
                eEntity, Utils.toLabel(eEntity.getClass().getSimpleName()), eVCriteriaKey, eVCriteriaValue
        );
    }


}

