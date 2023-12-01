package com.tiger.pocs.service.publisher;

import com.tiger.pocs.payload.SampleResponse;

import java.util.List;

public class MessagePublisherImpl implements MessagePublisher {
    @Override
    public void sendNotification(SampleResponse message, List<String> emails) {
        // TODO document why this method is empty
    }


    public <T> T validateRow(T inputRow) {

        return inputRow;
    }


}
