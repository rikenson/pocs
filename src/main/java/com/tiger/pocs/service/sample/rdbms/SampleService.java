package com.tiger.pocs.service.sample.rdbms;


import com.tiger.pocs.domain.TestSampleRequest;
import com.tiger.pocs.payload.SampleRequest;
import com.tiger.pocs.payload.SampleResponse;
import com.tiger.pocs.payload.SampleRequest;
import com.tiger.pocs.payload.SampleResponse;
import com.tiger.pocs.domain.filter.SampleFilter;

import java.util.List;
import java.util.UUID;

public interface SampleService {

    SampleResponse add(TestSampleRequest request);

    SampleResponse edit(SampleResponse request, String uuid);
    SampleResponse partialEdit(SampleRequest request, String uuid);

    SampleResponse retrieve(String uuid);

    List<SampleResponse> retrieveAll();

    List<SampleResponse> retrieveByCriteria(SampleFilter filter);

    void remove(String uuid);
}
