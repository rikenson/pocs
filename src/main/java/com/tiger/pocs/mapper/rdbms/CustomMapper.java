package com.tiger.pocs.mapper.rdbms;

import com.tiger.pocs.domain.TestSampleRequest;
import com.tiger.pocs.domain.entity.rdbms.SampleEntity;
import com.tiger.pocs.payload.ParamResponse;
import com.tiger.pocs.payload.SampleRequest;
import com.tiger.pocs.payload.SampleResponse;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;
import software.amazon.awssdk.services.ssm.model.Parameter;


@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CustomMapper {

    //    @Mapping(target = "uuid", defaultExpression = "java(UUID.randomUUID
    SampleEntity requestToSampleEntity(TestSampleRequest request);

    SampleResponse entityToSampleResponse(SampleEntity entity);

    SampleEntity updatedRequestToSampleEntity(SampleResponse request);

    ParamResponse awsResponseToSampleResponse(GetParameterResponse response);

    ParamResponse awsPathResponseToSampleResponse(Parameter response);

    void mapProps(@MappingTarget SampleEntity entity, SampleRequest request);

}