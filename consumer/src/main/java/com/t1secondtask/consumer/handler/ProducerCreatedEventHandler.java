package com.t1secondtask.consumer.handler;

import com.t1secondtask.consumer.entity.DataMetricEntity;
import com.t1secondtask.consumer.repository.DataMetricRepository;
import com.t1secondtask.modelsources.events.DataMetricBatchEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@KafkaListener(topics = "Product-created-events.topic")
@Slf4j
public class ProducerCreatedEventHandler {
    private final RestTemplate restTemplate;
    private final DataMetricRepository dataMetricRepository;

    public ProducerCreatedEventHandler(RestTemplate restTemplate,
                                       DataMetricRepository dataMetricRepository) {
        this.restTemplate = restTemplate;
        this.dataMetricRepository = dataMetricRepository;
    }

    @KafkaHandler
    @Transactional
    public void handle(DataMetricBatchEvent dataMetricBatchEvent) {
        log.info("Received data metric batch event in the form: {}", dataMetricBatchEvent.getIds());
        dataMetricRepository.saveAll(dataExtraction(dataMetricBatchEvent));
    }

    private List<DataMetricEntity> dataExtraction(DataMetricBatchEvent dataMetricBatchEvent) {
        return dataMetricBatchEvent.getEvents().stream()
                .map(metricDto -> new DataMetricEntity(
                        UUID.fromString(metricDto.getId()),
                        metricDto.getMetricName(),
                        metricDto.getTags(),
                        metricDto.getMeasurement(),
                        LocalDateTime.now()))
                .toList();
    }
}
