package com.kafka.provider;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class SpringBootForKafkaProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootForKafkaProviderApplication.class, args);
	}

    /**
     * Sends test messages to different Kafka topics when the application starts.
     *
     * @param kafkaTemplate KafkaTemplate used to send messages to Kafka
     * @return CommandLineRunner that executes after the application context is loaded
     */
    @Bean
    CommandLineRunner init(KafkaTemplate<String, String> kafkaTemplate) {
        return args -> {
            kafkaTemplate.send("topicAndres", "Hello World from SpringBoot");
            kafkaTemplate.send("newTopic", "Final Test Spring Boot");

        };
    }
}
