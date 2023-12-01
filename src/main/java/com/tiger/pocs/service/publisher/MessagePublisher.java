package com.tiger.pocs.service.publisher;


import com.tiger.pocs.payload.SampleResponse;

import java.util.List;

public interface MessagePublisher {

    void sendNotification(SampleResponse message, List<String> emails);
}
