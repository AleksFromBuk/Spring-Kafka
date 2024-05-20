package com.t1secondtask.producer.service;

import com.t1secondtask.modelsources.events.DataMetricEvent;
import com.t1secondtask.producer.service.dto.DataMetricDto;

import java.util.List;

public interface DataMetricEventsGenerateService {
    List<DataMetricEvent> getCommonInfoByAllMetrics();
    List<DataMetricEvent> getCommonInfoByAllMetrics(DataMetricDto[] inputDataMetricList);
}
