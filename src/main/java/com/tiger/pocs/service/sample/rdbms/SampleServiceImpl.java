package com.tiger.pocs.service.sample.rdbms;

import com.tiger.pocs.domain.filter.SampleFilter;
import com.tiger.pocs.exception.NotFoundException;
import com.tiger.pocs.mapper.rdbms.CustomMapper;
import com.tiger.pocs.payload.SampleRequest;
import com.tiger.pocs.payload.SampleResponse;
import com.tiger.pocs.repository.rdbms.SampleRepository;
import com.tiger.pocs.service.criteria.SampleSpecification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tiger.pocs.utils.FixedValues.NOT_FOUND_EXCEPTION_MSG;

@Service
public class SampleServiceImpl implements SampleService {

    private final CustomMapper converter;
    private final SampleRepository repository;

    public SampleServiceImpl(CustomMapper converter, SampleRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }

    @Override
    public SampleResponse add(SampleRequest request) {
        return converter.entityToSampleResponse(repository.save(converter.requestToSampleEntity(request)));
    }

    @Override
    public SampleResponse edit(SampleResponse request, String uuid) {

        if (!repository.existsById(uuid))
            throw new NotFoundException(String.format(NOT_FOUND_EXCEPTION_MSG, uuid));
        var toBeUpdateEntity = converter.updatedRequestToSampleEntity(request);
        toBeUpdateEntity.setUuid(uuid);
        return converter.entityToSampleResponse(repository.save(toBeUpdateEntity));
    }

    @Override
    public SampleResponse partialEdit(SampleRequest request, String uuid) {

        var toBeUpdated = repository
                .findById(uuid)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_EXCEPTION_MSG, uuid)));
        converter.mapProps(toBeUpdated, request);
        return converter.entityToSampleResponse(toBeUpdated);
    }


    @Override
    public SampleResponse retrieve(String uuid) {
        var found = repository.findById(uuid);
        if (found.isEmpty())
            throw new NotFoundException(String.format(NOT_FOUND_EXCEPTION_MSG, uuid));
        return converter.entityToSampleResponse(found.get());
    }

    @Override
    public List<SampleResponse> retrieveAll() {
        return repository
                .findAll()
                .stream()
                .map(converter::entityToSampleResponse)
                .toList();
    }

    @Override
    public List<SampleResponse> retrieveByCriteria(SampleFilter filter) {
        return repository
                .findAll(new SampleSpecification(filter))
                .stream()
                .map(converter::entityToSampleResponse)
                .toList();
    }

    @Override
    public void remove(String uuid) {
        if (!repository.existsById(uuid))
            throw new NotFoundException(String.format(NOT_FOUND_EXCEPTION_MSG, uuid));
        repository.deleteById(uuid);
    }


}
