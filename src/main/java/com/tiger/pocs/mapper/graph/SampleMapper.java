package com.tiger.pocs.mapper.graph;

import com.tiger.pocs.domain.entity.graph.SampleEdgeEntity;
import com.tiger.pocs.domain.entity.graph.SampleEntity;
import com.tiger.pocs.payload.SampleEdge;
import com.tiger.pocs.payload.SampleRequest;
import com.tiger.pocs.payload.SampleResponse;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SampleMapper {

    SampleEntity requestToSampleEntity(SampleRequest request);


    SampleEdgeEntity requestToSampleEdgeEntity(SampleEdge request);

    SampleResponse entityToSampleResponse(SampleEntity entity);


}