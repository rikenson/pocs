package com.tiger.pocs.service.sample.graph;


import com.tiger.pocs.payload.PatchSampleRequest;
import com.tiger.pocs.payload.SampleEdge;
import com.tiger.pocs.payload.SampleRequest;
import com.tiger.pocs.payload.SampleResponse;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface SampleGraphService {

    SampleResponse add(@NotNull final SampleRequest request);

    SampleResponse edit(@NotNull final PatchSampleRequest request, @NotNull final String uuid);

    SampleResponse retrieve(@NotNull final String uuid);

    List<SampleResponse> retrieveAll();

    List<SampleResponse> retrieveByKeyValue(@NotNull final String key, @NotNull final String value);

    Long remove(String key, String value);

    Object linkExistingVertices(@NotNull final SampleEdge edge,
                                @NotNull final String edgeLabel,
                                @NotNull final String sVertexLabel,
                                @NotNull final String sVertexId,
                                @NotNull final String eVertexLabel,
                                @NotNull final String eVertexId);

    Object linkVertices(@NotNull final SampleEdge edge,
                        @NotNull final String edgeLabel,
                        @NotNull final SampleRequest sVertex,
                        @NotNull final String sVCriteriaKey,
                        @NotNull final String sVCriteriaValue,
                        @NotNull final SampleRequest eVertex,
                        @NotNull final String eVCriteriaKey,
                        @NotNull final String eVCriteriaValue
    );
}
