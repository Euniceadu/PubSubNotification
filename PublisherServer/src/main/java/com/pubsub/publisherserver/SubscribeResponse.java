package com.pubsub.publisherserver;


public class SubscribeResponse {

    private final String url;
    private final String topic;
    private final String errorMessage;

    public SubscribeResponse(String url, String topic) {
        this.url = url;
        this.topic = topic;
        this.errorMessage = null;
    }

    public SubscribeResponse(String errorMessage) {
        this.url = null;
        this.topic = null;
        this.errorMessage = errorMessage;
    }


    public String getUrl() {
        return url;
    }

    public String getTopic() {
        return topic;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}