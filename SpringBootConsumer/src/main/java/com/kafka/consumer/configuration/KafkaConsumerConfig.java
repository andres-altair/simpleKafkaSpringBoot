package com.kafka.consumer.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka Consumer Configuration class for setting up consumer properties and listener container factory.
 */
@Configuration
public class KafkaConsumerConfig {

    // Injects the Kafka bootstrap server address from the application properties
    @Value("${spring.kafka.bootstrapServers}")
    private String bootstrapServers;

    /**
     * Returns the configuration properties required to set up a Kafka consumer.
     *
     * @return a Map containing Kafka consumer configurations
     */
    public Map<String, Object> consumerConfig() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return properties;
    }

    /**
     * Creates a ConsumerFactory using the defined consumer configuration.
     *
     * @return a ConsumerFactory<String, String> instance
     */
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    /**
     * Creates a KafkaListenerContainerFactory which is required for @KafkaListener annotations
     * to create Kafka consumers and manage the listener containers.
     *
     * @return a ConcurrentKafkaListenerContainerFactory for concurrent Kafka listeners
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> consumer() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
