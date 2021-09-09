package com.pubsub.publisherserver;


public class SubscribeResponse {

    private final String url;
    private final String topic;
    private final String message;
    private final boolean success;

    public SubscribeResponse(String url, String topic, boolean success) {
        this.url = url;
        this.topic = topic;
        this.message = null;
        this.success = success;
    }

    public SubscribeResponse(String message, boolean success) {
        this.url = null;
        this.topic = null;
        this.message = message;
        this.success = success;
    }


    public String getUrl() {
        return url;
    }

    public String getTopic() {
        return topic;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}