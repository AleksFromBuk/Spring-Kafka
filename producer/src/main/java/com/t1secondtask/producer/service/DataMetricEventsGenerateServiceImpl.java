package com.t1secondtask.producer.service;


import com.t1secondtask.modelsources.events.DataMetricEvent;
import com.t1secondtask.producer.service.dto.DataMetricDto;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DataMetricEventsGenerateServiceImpl implements DataMetricEventsGenerateService {
    private final MeterRegistry meterRegistry;
    private final Set<String> importantMetrics = Set.of("jvm.memory.used", "jvm.memory.max", "system.cpu.usage", "system.memory.usage", "jvm.gc.pause");

    public DataMetricEventsGenerateServiceImpl(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public List<DataMetricEvent> getCommonInfoByAllMetrics() {
        return meterRegistry.getMeters().stream()
                .filter(meter -> importantMetrics.contains(meter.getId().getName()))
                .map(meter -> new DataMetricEvent(
                        UUID.randomUUID().toString(),
                        meter.getId().getName(),
                        meter.getId().getTags()
                                .stream()
                                .collect(Collectors.toMap(Tag::getKey, Tag::getValue)),
                        meter.measure().iterator().next().getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DataMetricEvent> getCommonInfoByAllMetrics(DataMetricDto[] inputDataMetrics) {
        return Arrays.stream(inputDataMetrics)
                .map(dto -> new DataMetricEvent(
                        UUID.randomUUID().toString(),
                        dto.getMetricName(),
                        dto.getTags(),
                        dto.getMeasurement()))
                .collect(Collectors.toList());
    }
}
