package com.pubsub.publisherserver;

public class PublishResponse {

    private final String message;
    private final boolean success;

    public PublishResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}