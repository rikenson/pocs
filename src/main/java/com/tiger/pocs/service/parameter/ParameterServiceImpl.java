package com.tiger.pocs.service.parameter;

import com.tiger.pocs.mapper.rdbms.CustomMapper;
import com.tiger.pocs.payload.ParamRequest;
import com.tiger.pocs.payload.ParamResponse;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.*;

import java.util.List;

@Service
public class ParameterServiceImpl implements ParameterService {

    private final SsmClient ssmClient;
    private final CustomMapper convert;

    public ParameterServiceImpl(SsmClient ssmClient, CustomMapper convert) {
        this.ssmClient = ssmClient;
        this.convert = convert;
    }

    @Override
    public void add(ParamRequest request) {
        try {
            PutParameterRequest parameterRequest = PutParameterRequest.builder()
                    .name(request.getName())
                    .type(this.getType(request.getType())).value(request.getValue())
                    .build();
            ssmClient.putParameter(parameterRequest);
            ssmClient.close();
        } catch (SsmException e) {
            System.exit(1);
        }
    }

    @Override
    public ParamResponse getParam(String name) {
        GetParameterResponse parameterResponse = null;
        try {
            GetParameterRequest parameterRequest = GetParameterRequest
                    .builder()
                    .name(name)
                    .build();
            parameterResponse = ssmClient.getParameter(parameterRequest);
        } catch (SsmException e) {
            System.exit(1);
        }
        return convert.awsResponseToSampleResponse(parameterResponse);
    }

    @Override
    public List<ParamResponse> getParamsByPath(String path) {
        GetParametersByPathResponse response = null;
        try {
            GetParametersByPathRequest request = GetParametersByPathRequest
                    .builder()
                    .path(path)
                    .recursive(true)
                    .build();
            response = ssmClient.getParametersByPath(request);
        } catch (SsmException e) {
            System.exit(1);
        }
        return response
                .parameters()
                .stream()
                .map(convert::awsPathResponseToSampleResponse)
                .toList();
    }

    private ParameterType getType(String name) {
        return switch (name) {
            case "secure_string" -> ParameterType.SECURE_STRING;
            case "string_list" -> ParameterType.STRING_LIST;
            default -> ParameterType.STRING;
        };
    }

}
