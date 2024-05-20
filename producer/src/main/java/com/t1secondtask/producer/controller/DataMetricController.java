package com.t1secondtask.producer.controller;

import com.t1secondtask.producer.service.DataMetricEventsService;
import com.t1secondtask.producer.service.dto.DataMetricDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/metrics")
@Slf4j
public class DataMetricController {
    private final DataMetricEventsService dataMetricEventsService;

    public DataMetricController(DataMetricEventsService dataMetricEventsService) {
        this.dataMetricEventsService = dataMetricEventsService;
    }

    @PostMapping
    public ResponseEntity<Object> createData(@RequestBody @Valid DataMetricDto[] dataEntity) {
        List<String> result = dataMetricEventsService.createAndSendDataMetricEvents(dataEntity);
        log.info("Data processed successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}