package com.t1secondtask.producer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataMetric {
    private String metricName;
    private Map<String, String> tags = new HashMap<>();
    private double measurement;
}
