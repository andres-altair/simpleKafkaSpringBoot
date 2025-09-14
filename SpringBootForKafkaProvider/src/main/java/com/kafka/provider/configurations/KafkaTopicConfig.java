package com.kafka.provider.configurations;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * KafkaTopicConfig defines a Spring Bean responsible for creating a Kafka topic
 */
@Configuration
public class KafkaTopicConfig {
    /**
     * Bean to create a new Kafka topic with custom configurations.
     *
     * @return NewTopic instance with the specified configurations
     */
    @Bean
    public NewTopic generateTopic() {
        // Define custom configuration for the Kafka topic
        Map<String, String> configurations = new HashMap<>();
        // Set the cleanup policy to 'delete'
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        // Set the retention time to 1 day (in milliseconds)
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");
        // Set the segment file size to 1 GB (in bytes)
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1048576000");
        // Set the maximum size of a single message to 1 GB
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1048576000");

        // Build and return the new topic with the specified name, partitions, replicas, and configs
        return TopicBuilder.name("newTopic")
                .partitions(3)
                .replicas(1)
                .configs(configurations)
                .build();
    }
}
