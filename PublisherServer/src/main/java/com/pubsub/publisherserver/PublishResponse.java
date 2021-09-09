package com.pubsub.publisherserver;

public class PublishResponse {

    private final String errorMessage;

    public PublishResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}

//topic: string
//        data: object