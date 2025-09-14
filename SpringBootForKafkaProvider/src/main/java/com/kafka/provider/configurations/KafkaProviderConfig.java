package com.kafka.provider.configurations;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * KafkaProviderConfig is a configuration class that sets up the Kafka producer components
 * required for sending messages to a Kafka topic.
 */
@Configuration
public class KafkaProviderConfig {

    // Injects the Kafka bootstrap server address from application properties
    @Value("${spring.kafka.bootstrapServers}")
    private String bootstrapServers;

    /**
     * Returns the configuration properties required to set up a Kafka producer.
     *
     * @return a Map containing Kafka producer configurations such as:
     * - bootstrap servers (Kafka cluster address)
     * - key serializer (StringSerializer)
     * - value serializer (StringSerializer)
     */
    public Map<String, Object> producerConfig() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return properties;
    }

    /**
     * Creates and returns a Kafka ProducerFactory using the provided configuration.
     * This factory will be used to create Kafka producers.
     *
     * @return a ProducerFactory for String keys and String values
     */
    @Bean
    public ProducerFactory<String, String> providerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    /**
     * Creates and returns a KafkaTemplate, which provides high-level methods
     * for sending messages to Kafka topics.
     *
     * @param producerFactory the producer factory to use
     * @return a KafkaTemplate for sending messages with String keys and values
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
