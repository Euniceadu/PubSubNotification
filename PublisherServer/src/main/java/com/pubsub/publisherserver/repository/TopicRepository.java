package com.pubsub.publisherserver.repository;

import com.pubsub.publisherserver.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Topic findByName(String name);
}
