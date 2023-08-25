package com.tiger.pocs.mapper;

import com.tiger.pocs.domain.entity.SampleEntity;
import com.tiger.pocs.payload.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;
import software.amazon.awssdk.services.ssm.model.Parameter;


@Mapper(componentModel = "spring")
public interface CustomMapper {
    @Mapping(target = "createdBy", constant = "anonymous")
    SampleEntity requestToSampleEntity(SampleRequest request);

    SampleResponse entityToSampleResponse(SampleEntity entity);

    SampleEntity updatedRequestToSampleEntity(UpdatedSampleRequest request);

    ParamResponse awsResponseToSampleResponse(GetParameterResponse response);

    ParamResponse awsPathResponseToSampleResponse(Parameter response);

    void mapProps(@MappingTarget SampleEntity entity, PatchedSampleRequest request);

}