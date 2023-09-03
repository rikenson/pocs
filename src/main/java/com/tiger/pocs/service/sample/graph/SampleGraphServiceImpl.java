package com.tiger.pocs.service.sample.graph;

import com.google.gson.Gson;
import com.tiger.pocs.domain.entity.SampleEntity;
import com.tiger.pocs.mapper.CustomMapper;
import com.tiger.pocs.payload.SampleRequest;
import com.tiger.pocs.payload.SampleResponse;
import com.tiger.pocs.repository.graph.GraphSampleRepository;
import com.tiger.pocs.utils.Utills;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleGraphServiceImpl implements SampleGraphService {


    private final CustomMapper converter;
    private final GraphSampleRepository repository;

    private final Gson customGson;

    public SampleGraphServiceImpl(CustomMapper converter, GraphSampleRepository repository, Gson customGson) {
        this.converter = converter;
        this.repository = repository;
        this.customGson = customGson;
    }

    @Override
    public SampleResponse add(SampleRequest request) {
        var entity = converter.requestToSampleEntity(request);
        var saved = repository.createVertex(entity, Utills.getClassNameSuffix(SampleRequest.class.getSimpleName()));
        return converter.entityToSampleResponse(customGson.fromJson(customGson.toJson(saved), SampleEntity.class));
    }

    @Override
    public SampleResponse edit(SampleResponse request, String uuid) {
        return null;
    }

    @Override
    public SampleResponse partialEdit(SampleRequest request, String uuid) {
        return null;
    }

    @Override
    public SampleResponse retrieve(String uuid) {
        var data = repository.findByKey(Utills.getClassNameSuffix(SampleRequest.class.getSimpleName()), "uuid", uuid);
        return data.stream()
                .map(o -> customGson.fromJson(customGson.toJson(o), SampleResponse.class))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public List<SampleResponse> retrieveAll() {

        var data = repository.findAll(Utills.getClassNameSuffix(SampleRequest.class.getSimpleName()));
        return data.stream()
                .map(o -> customGson.fromJson(customGson.toJson(o), SampleResponse.class))
                .toList();
    }

    @Override
    public List<SampleResponse> retrieveByKeyValue(String key, String value) {
        var data = repository.findByKey(Utills.getClassNameSuffix(SampleRequest.class.getSimpleName()), key, value);
        return data.stream()
                .map(o -> customGson.fromJson(customGson.toJson(o), SampleResponse.class))
                .toList();
    }

    @Override
    public void remove(String uuid) {

    }
}
