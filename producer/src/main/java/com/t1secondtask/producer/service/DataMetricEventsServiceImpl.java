package com.t1secondtask.producer.service;

import com.t1secondtask.modelsources.events.DataMetricBatchEvent;
import com.t1secondtask.modelsources.events.DataMetricEvent;
import com.t1secondtask.producer.service.dto.DataMetricDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class DataMetricEventsServiceImpl implements DataMetricEventsService {
    private final DataMetricEventsGenerateService dataMetricEventsGenerateService;
    private final KafkaTemplate<String, DataMetricBatchEvent> kafkaTemplate;
    private final ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    public DataMetricEventsServiceImpl(DataMetricEventsGenerateService dataMetricEventsGenerateService,
                                       KafkaTemplate<String, DataMetricBatchEvent> kafkaTemplate,
                                       ThreadPoolTaskScheduler taskScheduler) {
        this.dataMetricEventsGenerateService = dataMetricEventsGenerateService;
        this.kafkaTemplate = kafkaTemplate;
        this.taskScheduler = taskScheduler;
        scheduleDataMetricEvents();
    }

    private void scheduleDataMetricEvents() {
        taskScheduler.scheduleAtFixedRate(this::createAndSendDataMetricEvents, Instant.now(), Duration.ofSeconds(5));
    }

    @Override
    public void createAndSendDataMetricEvents() {
        log.info("current Thread name: {}", Thread.currentThread().getName());
        List<DataMetricEvent> metrics = dataMetricEventsGenerateService.getCommonInfoByAllMetrics();
        List<List<DataMetricEvent>> batches = getBatches(metrics);
        for (List<DataMetricEvent> batch : batches) {
            sendDataMetricEvents(batch);
        }
    }

    @Override
    public List<String> createAndSendDataMetricEvents(DataMetricDto[] inputDataMetrics) {
        List<String> ids = new ArrayList<>();
        List<DataMetricEvent> events = dataMetricEventsGenerateService.getCommonInfoByAllMetrics(inputDataMetrics);
        List<List<DataMetricEvent>> batches = getBatches(events);
        for (List<DataMetricEvent> batch : batches) {
            ids.addAll(sendDataMetricEvents(batch));
        }
        return ids;
    }

    @Override
    public List<String> sendDataMetricEvents(List<DataMetricEvent> events) {
        List<String> ids = events.stream()
                .map(DataMetricEvent::getId)
                .collect(Collectors.toList());
        DataMetricBatchEvent batch = new DataMetricBatchEvent(ids, events);
        CompletableFuture<SendResult<String, DataMetricBatchEvent>> future = kafkaTemplate
                .send("Product-created-events.topic", batch);

        future.whenComplete((result, error) -> {
            if (error != null) {
                log.error("Failed to send message: {}", error.getMessage(), error);
            } else {
                log.info("Successfully created data metric {}", result.getRecordMetadata());
                log.info("Return {}", ids);
                log.info("Topic {}", result.getRecordMetadata().topic());
                log.info("Partition {}", result.getRecordMetadata().partition());
                log.info("Offset {}", result.getRecordMetadata().offset());
                // TODO save results into BD
            }
        });
        return ids;
    }

    private static List<List<DataMetricEvent>> getBatches(List<DataMetricEvent> metrics) {
        int batchSize = 10;
        return IntStream.range(0, (metrics.size() + batchSize - 1) / batchSize)
                .mapToObj(i -> metrics.subList(i * batchSize, Math.min((i + 1) * batchSize, metrics.size())))
                .collect(Collectors.toList());
    }
}
