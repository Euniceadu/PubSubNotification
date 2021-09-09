package com.pubsub.publisherserver.domain;

import javax.persistence.*;

@Entity
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String url;

    @ManyToOne
    private Topic topic;

    public Subscriber() {

    }

    public Subscriber(String url) {
        setUrl(url);
    }

    public Subscriber(String url, Topic topic) {
        setUrl(url);
        setTopic(topic);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
