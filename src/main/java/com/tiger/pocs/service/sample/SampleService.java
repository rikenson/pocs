package com.tiger.pocs.service.sample;


import com.tiger.pocs.payload.PatchedSampleRequest;
import com.tiger.pocs.payload.UpdatedSampleRequest;
import com.tiger.pocs.payload.SampleRequest;
import com.tiger.pocs.payload.SampleResponse;
import com.tiger.pocs.domain.filter.SampleFilter;

import java.util.List;
import java.util.UUID;

public interface SampleService {

    SampleResponse add(SampleRequest request);

    SampleResponse edit(UpdatedSampleRequest request, String uuid);
    SampleResponse partialEdit(PatchedSampleRequest request, String uuid);

    SampleResponse retrieve(String uuid);

    List<SampleResponse> retrieveAll();

    List<SampleResponse> retrieveByCriteria(SampleFilter filter);

    void remove(String uuid);
}
