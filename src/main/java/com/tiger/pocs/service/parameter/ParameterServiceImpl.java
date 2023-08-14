package com.tiger.pocs.service.parameter;

import com.tiger.pocs.mapper.ParamsMapper;
import com.tiger.pocs.payload.ParamRequest;
import com.tiger.pocs.payload.ParamResponse;
import com.tiger.pocs.payload.Regulation;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.*;

import java.util.List;

@Service
public class ParameterServiceImpl implements ParameterService {

    private final SsmClient ssmClient;
    private final ParamsMapper mapper;

    public ParameterServiceImpl(SsmClient ssmClient, ParamsMapper mapper) {
        this.ssmClient = ssmClient;
        this.mapper = mapper;
    }

    @Override
    public void add(ParamRequest request) {
        try {
            PutParameterRequest parameterRequest = PutParameterRequest.builder()
                    .name(request.getName()).type(this.getType(request.getType())).value(request.getValue()).build();
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
            GetParameterRequest parameterRequest = GetParameterRequest.builder()
                    .name(name).build();
            parameterResponse = ssmClient.getParameter(parameterRequest);
        } catch (SsmException e) {
            System.exit(1);
        }
        return mapper.toResponse(parameterResponse);
    }

    @Override
    public List<ParamResponse> getParamsByPath(String path) {
        GetParametersByPathResponse response = null;
        try {
            GetParametersByPathRequest request = GetParametersByPathRequest.builder()
                    .path(path)
//                    .maxResults(5)
                    .recursive(true).build();
            response = ssmClient.getParametersByPath(request);
        } catch (SsmException e) {
            System.exit(1);
        }
        return mapper.toResponse(response);
    }

    @Override
    public Regulation loadPl17Params() {
        return Regulation.builder().build();
    }

    @Override
    public Regulation loadC30Params() {
        return Regulation.builder().build();
    }


    private ParameterType getType(String name) {
        return switch (name) {
            case "secure_string" -> ParameterType.SECURE_STRING;
            case "string_list" -> ParameterType.STRING_LIST;
            default -> ParameterType.STRING;
        };
    }

}
