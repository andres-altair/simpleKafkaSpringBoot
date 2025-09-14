package com.kafka.consumer.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Kafka consumer listener that listens to messages from the "newTopic" topic.
 */
@Service
public class KafkaConsumerListener {

    // Logger instance for logging received messages
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerListener.class);

    /**
     * Method that listens for messages from the Kafka topic "newTopic".
     *
     * @param message The message received from the topic
     */
    @KafkaListener(topics = {"newTopic"}, groupId = "my-group")
    public void listener(String message) {
        logger.info("Received message: {}", message);
    }
}
