package com.t1secondtask.modelsources.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataMetricEvent {
    private String id;
    private String metricName;
    private Map<String, String> tags = new HashMap<>();
    private double measurement;
}
