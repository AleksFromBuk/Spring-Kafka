package com.t1secondtask.consumer.repository;

import com.t1secondtask.consumer.entity.DataMetricEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataMetricRepository extends CrudRepository<DataMetricEntity, Long> {
    Optional<DataMetricEntity> findByMetricName(String metricName);

    List<DataMetricEntity> findAllByMetricName(String metricName);

    @Query("SELECT MAX(d.measurement) FROM DataMetricEntity d WHERE d.metricName = :metricName")
    Double findMaxMeasurementByMetricName(@Param("metricName") String metricName);

    @Query("SELECT MIN(d.measurement) FROM DataMetricEntity d WHERE d.metricName = :metricName")
    Double findMinMeasurementByMetricName(@Param("metricName") String metricName);

    @Query("SELECT AVG(d.measurement) FROM DataMetricEntity d WHERE d.metricName = :metricName")
    Double findAvgMeasurementByMetricName(@Param("metricName") String metricName);
}
