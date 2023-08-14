package com.tiger.pocs.service;

import com.tiger.pocs.payload.WorkshopResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagePublisherImpl implements MessagePublisher {
    @Override
    public void sendNotification(WorkshopResponse message, List<String> emails) {
        // TODO document why this method is empty
    }

}
