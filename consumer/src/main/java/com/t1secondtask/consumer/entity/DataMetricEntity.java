package com.t1secondtask.consumer.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataMetricEntity {
    @Id
    @UuidGenerator
    private UUID id;
    @NotNull
    private String metricName;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, String> tags = new HashMap<>();
    @NotNull
    private double measurement;
    @NotNull
    private LocalDateTime createdAt;
}
