package com.t1secondtask.consumer.controller;

import com.t1secondtask.consumer.entity.DataMetricEntity;
import com.t1secondtask.consumer.service.DataMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/metrics")
public class DataMetricController {

    private final DataMetricService dataMetricService;

    @Autowired
    public DataMetricController(DataMetricService dataMetricService) {
        this.dataMetricService = dataMetricService;
    }

    @GetMapping
    public ResponseEntity<Iterable<DataMetricEntity>> getAllMetrics() {
        return ResponseEntity.ok(dataMetricService.getAllMetrics());
    }

    @GetMapping("/{metricName}")
    public ResponseEntity<List<DataMetricEntity>> getMetricsByName(@PathVariable String metricName) {
        return ResponseEntity.ok((dataMetricService.getMetricByName(metricName)));
    }

    @GetMapping("/{metricName}/max")
    public ResponseEntity<Double> getMaxMeasurementByMetricName(@PathVariable String metricName) {
        return ResponseEntity.ok(dataMetricService.findMaxMeasurementByMetricName(metricName));
    }

    @GetMapping("/{metricName}/min")
    public ResponseEntity<Double> getMinMeasurementByMetricName(@PathVariable String metricName) {
        return ResponseEntity.ok(dataMetricService.findMinMeasurementByMetricName(metricName));
    }

    @GetMapping("/{metricName}/avg")
    public ResponseEntity<Double> getAvgMeasurementByMetricName(@PathVariable String metricName) {
        return ResponseEntity.ok(dataMetricService.findAvgMeasurementByMetricName(metricName));
    }
}