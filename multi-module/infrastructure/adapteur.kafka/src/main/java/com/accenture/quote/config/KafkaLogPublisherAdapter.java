package com.accenture.quote.config;

import com.accenture.quote.model.log.AppLogEvent;
import com.accenture.quote.ports.output.LogPublisherPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaLogPublisherAdapter implements LogPublisherPort {

    private static final String TOPIC = "app-logs";
    private final KafkaTemplate<String, AppLogEvent> kafkaTemplate;

    public KafkaLogPublisherAdapter(KafkaTemplate<String, AppLogEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(AppLogEvent event) {
        kafkaTemplate.send(TOPIC, event.entityId().toString(), event);
    }

}
