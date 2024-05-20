package com.t1secondtask.producer.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataMetricDto implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    @NotBlank(message = "Metric name is required")
    private String metricName;
    @NotEmpty(message = "Tags are required")
    private Map<String, String> tags = new HashMap<>();
    @NotNull(message = "Measurement is required")
    private double measurement;
}
