package com.tiger.pocs.service.sample.graph;


import com.tiger.pocs.domain.filter.SampleFilter;
import com.tiger.pocs.payload.SampleRequest;
import com.tiger.pocs.payload.SampleRequest;
import com.tiger.pocs.payload.SampleResponse;
import com.tiger.pocs.payload.SampleResponse;

import java.util.List;

public interface SampleGraphService {

    SampleResponse add(SampleRequest request);

    SampleResponse edit(SampleResponse request, String uuid);
    SampleResponse partialEdit(SampleRequest request, String uuid);

    SampleResponse retrieve(String uuid);

    List<SampleResponse> retrieveAll();

    List<SampleResponse> retrieveByKeyValue(String key, String value);

    void remove(String uuid);
}
