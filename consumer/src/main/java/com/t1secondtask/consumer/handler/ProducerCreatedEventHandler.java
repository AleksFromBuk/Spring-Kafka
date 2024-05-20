package com.t1secondtask.consumer.handler;

import com.t1secondtask.modelsources.events.DataMetricBatchEvent;
import lombok.extern.slf4j.Slf4j;
import com.t1secondtask.consumer.exceptions.NonRetryableException;
import com.t1secondtask.consumer.exceptions.RetryableException;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
@KafkaListener(topics = "Product-created-events.topic")
@Slf4j
public class ProducerCreatedEventHandler {
    private final RestTemplate restTemplate;

    public ProducerCreatedEventHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @KafkaHandler
    public void handle(DataMetricBatchEvent dataMetricBatchEvent) {
        log.info("Received data metric batch event in the form: {}", dataMetricBatchEvent.getIds());

        //fakeIntegrationWithOtherComponents();
    }

    private void fakeIntegrationWithOtherComponents() {
        String url = "http://localhost:8080/mock";
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Received response: {}", response.getBody());
            }
        } catch (ResourceAccessException ex) {
            log.error(ex.getMessage(), ex);
            throw new RetryableException(ex);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new NonRetryableException(ex);
        }
    }
}
