package com.example.currency_server.config;

import com.example.currency_server.model.Currency;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    private String kafkaServe = "localhost:9092";

    @Bean
    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServe);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return  props;
    }
    @Bean
    public ProducerFactory<String, List<Currency>> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }
    @Bean
    public KafkaTemplate<String,  List<Currency>> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
