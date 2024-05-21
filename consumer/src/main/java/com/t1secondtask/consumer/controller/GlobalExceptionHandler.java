package com.t1secondtask.consumer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.t1secondtask.consumer.exceptions.MetricNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MetricNotFoundException.class)
    public ResponseEntity<String> handleMetricNotFound(MetricNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}