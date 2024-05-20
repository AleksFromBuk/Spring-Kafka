package com.t1secondtask.producer.service;

import com.t1secondtask.modelsources.events.DataMetricEvent;
import com.t1secondtask.producer.service.dto.DataMetricDto;

import java.util.List;

public interface DataMetricEventsService {
    List<String> createAndSendDataMetricEvents(DataMetricDto[] inputDataMetrics);
    void createAndSendDataMetricEvents();
    List<String> sendDataMetricEvents(List<DataMetricEvent> events);
}
