package com.t1secondtask.consumer.exceptions;

public class MetricNotFoundException extends RuntimeException {
    public MetricNotFoundException(String metricName) {
        super(String.format("Metric %s not found: ", metricName));
    }
}
