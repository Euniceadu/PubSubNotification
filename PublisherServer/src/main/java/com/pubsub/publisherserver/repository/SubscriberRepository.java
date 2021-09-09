package com.pubsub.publisherserver.repository;

import com.pubsub.publisherserver.domain.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

//    Topic findByName(String name);
}
