package com.pubsub.publisherserver;

import com.google.gson.Gson;
import com.pubsub.publisherserver.domain.Subscriber;
import com.pubsub.publisherserver.domain.Topic;
import com.pubsub.publisherserver.repository.SubscriberRepository;
import com.pubsub.publisherserver.repository.TopicRepository;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@RequestMapping("")
@RestController
public class Controller {

    private final TopicRepository topicRepository;
    private final SubscriberRepository subscriberRepository;
    private final OkHttpClient client;
    private final Gson gson;


    @Autowired
    public Controller(TopicRepository topicRepository, SubscriberRepository subscriberRepository) {
        this.topicRepository = topicRepository;
        this.subscriberRepository = subscriberRepository;
        client = new OkHttpClient.Builder().build();
        gson = new Gson();
    }

    @RequestMapping(value = "/create/{topic}", method = RequestMethod.POST)
    @ResponseBody
    public PublishResponse createTopic(@PathVariable(value = "topic") String topic) {
        Topic topicObject = topicRepository.findByName(topic);
        if (null == topicObject) {
            topicRepository.save(new Topic(topic));
            return new PublishResponse("Topic " + topic + " created successfully", true);
        } else {
            return new PublishResponse("Topic " + topic + " already exists", false);
        }
    }

    @RequestMapping(value = "/subscribe/{topic}", method = RequestMethod.POST)
    @ResponseBody
    public SubscribeResponse createSubscription(@PathVariable(value = "topic") String topic, @RequestBody SubscribeParams params) {
        if (null == params.getUrl() || params.getUrl().equals("")) {
            return new SubscribeResponse("Subscriber url cannot be null or empty", false);
        }

        Topic topicObject = topicRepository.findByName(topic);
        if (null == topicObject) {
            return new SubscribeResponse("Topic does not exist", false);
        } else {
            List<Subscriber> subscribers = topicObject.getSubscribers();
            for (Subscriber subscriber: subscribers) {
                if (params.getUrl().equals(subscriber.getUrl())) {
                    return new SubscribeResponse(params.getUrl(), topic, true);
                }
            }
            Subscriber subscriber = subscriberRepository.save(new Subscriber(params.getUrl(), topicObject));

            subscribers.add(subscriber);
            topicObject.setSubscribers(subscribers);
            topicRepository.save(topicObject);

            return new SubscribeResponse(params.getUrl(), topic, true);

        }
    }


    @RequestMapping(value = "/publish/{topic}", method = RequestMethod.POST)
    @ResponseBody
    public PublishResponse publishMessage(@PathVariable(value = "topic") String topic, @RequestBody Object data) {
        Topic topicObject = topicRepository.findByName(topic);
        if (null == topicObject) {
            return new PublishResponse("Topic does not exist", false);
        } else {
            List<Subscriber> subscribers = topicObject.getSubscribers();
            for (Subscriber subscriber: subscribers) {
                makeRequest(subscriber.getUrl(), data);
            }
            return new PublishResponse("Message published successfully", true);
        }
    }

    private void makeRequest(String url, Object data) {

        String json = gson.toJson(data);
        okhttp3.RequestBody body = okhttp3.RequestBody.create(json,
                MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = client.newCall(request);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
