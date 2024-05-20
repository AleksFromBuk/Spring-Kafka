package com.t1secondtask.modelsources.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataMetricBatchEvent {
    private List<String> ids;
    private List<DataMetricEvent> events;
}
