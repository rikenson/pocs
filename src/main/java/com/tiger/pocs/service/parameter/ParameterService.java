package com.tiger.pocs.service.parameter;


import com.tiger.pocs.payload.ParamRequest;
import com.tiger.pocs.payload.ParamResponse;

import java.util.List;

public interface ParameterService {
    void add(ParamRequest request);

    ParamResponse getParam(String name);

    List<ParamResponse> getParamsByPath(String path);



}
