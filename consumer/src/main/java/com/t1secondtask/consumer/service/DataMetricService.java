package com.t1secondtask.consumer.service;

import com.t1secondtask.consumer.entity.DataMetricEntity;
import com.t1secondtask.consumer.repository.DataMetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DataMetricService {
    private final DataMetricRepository dataMetricRepository;

    @Autowired
    public DataMetricService(DataMetricRepository dataMetricRepository) {
        this.dataMetricRepository = dataMetricRepository;
    }

    public Iterable<DataMetricEntity> getAllMetrics() {
        return dataMetricRepository.findAll();
    }

    public List<DataMetricEntity> getMetricByName(String name) {
        return dataMetricRepository.findAllByMetricName(name);
    }

    public Double findMaxMeasurementByMetricName(String metricName) {
        return dataMetricRepository.findMaxMeasurementByMetricName(metricName);

    }

    public Double findMinMeasurementByMetricName(String metricName) {
        return dataMetricRepository.findMinMeasurementByMetricName(metricName);
    }

    public Double  findAvgMeasurementByMetricName(String metricName) {
        return dataMetricRepository.findAvgMeasurementByMetricName(metricName);
    }
}
