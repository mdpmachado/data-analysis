package com.data.analysis.report.infra.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author Murillo Machado
 */
public abstract class AbstractProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    protected void send(String topic, Object object) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(object);
        this.kafkaTemplate.send(topic, json);
    }


    protected void send(String topic, String value) {
        this.kafkaTemplate.send(topic, value);
    }


}
