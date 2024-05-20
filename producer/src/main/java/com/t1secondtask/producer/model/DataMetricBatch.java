package com.t1secondtask.producer.model;


import com.t1secondtask.modelsources.events.DataMetricEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DataMetricBatch {
    private List<String> ids;
    private List<DataMetricEvent> events;
}
