package com.tiger.pocs.mapper;

import com.tiger.pocs.payload.ParamResponse;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;
import software.amazon.awssdk.services.ssm.model.GetParametersByPathResponse;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParamsMapper {


    public ParamResponse toResponse(GetParameterResponse response) {
        return ParamResponse.builder()
                .name(response.parameter().name())
                .value(response.parameter().value())
                .type(response.parameter().type().name())
                .version(response.parameter().version().intValue())
                .lastModifiedDate(LocalDateTime.ofInstant(response.parameter().lastModifiedDate(), ZoneOffset.UTC))
                .build();
    }

    public List<ParamResponse> toResponse(GetParametersByPathResponse response) {
        List<ParamResponse> responses = new ArrayList<>();
        response.parameters().forEach(parameter -> responses.add(ParamResponse.builder()
                .name(parameter.name())
                .value(parameter.value())
                .type(parameter.type().name())
                .version(parameter.version().intValue())
                .lastModifiedDate(LocalDateTime.ofInstant(parameter.lastModifiedDate(), ZoneOffset.UTC))
                .build()));

        return responses;
    }
}