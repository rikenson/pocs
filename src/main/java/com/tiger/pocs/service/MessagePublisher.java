package com.tiger.pocs.service;


import com.tiger.pocs.payload.WorkshopResponse;

import java.util.List;

public interface MessagePublisher {

    void sendNotification(WorkshopResponse message, List<String> emails);
}
