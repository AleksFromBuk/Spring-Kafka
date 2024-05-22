package com.t1secondtask.consumer.controller;

import com.t1secondtask.consumer.entity.DataMetricEntity;
import com.t1secondtask.consumer.repository.DataMetricRepository;
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

    private final DataMetricRepository  dataMetricRepository;

    @Autowired
    public DataMetricController(DataMetricRepository dataMetricRepository) {
        this.dataMetricRepository = dataMetricRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<DataMetricEntity>> getAllMetrics() {
        return ResponseEntity.ok(dataMetricRepository.findAll());
    }

    @GetMapping("/{metricName}")
    public ResponseEntity<List<DataMetricEntity>> getMetricsByName(@PathVariable String metricName) {
        return ResponseEntity.ok(dataMetricRepository.findAllByMetricName(metricName));
    }

    @GetMapping("/{metricName}/max")
    public ResponseEntity<Double> getMaxMeasurementByMetricName(@PathVariable String metricName) {
        return ResponseEntity.ok(dataMetricRepository.findMaxMeasurementByMetricName(metricName));
    }

    @GetMapping("/{metricName}/min")
    public ResponseEntity<Double> getMinMeasurementByMetricName(@PathVariable String metricName) {
        return ResponseEntity.ok(dataMetricRepository.findMinMeasurementByMetricName(metricName));
    }

    @GetMapping("/{metricName}/avg")
    public ResponseEntity<Double> getAvgMeasurementByMetricName(@PathVariable String metricName) {
        return ResponseEntity.ok(dataMetricRepository.findAvgMeasurementByMetricName(metricName));
    }
}