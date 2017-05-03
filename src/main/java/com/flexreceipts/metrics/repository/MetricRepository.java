package com.flexreceipts.metrics.repository;

import com.flexreceipts.metrics.model.Metric;

public interface MetricRepository {
	
	Metric save(Metric entity);
	
	Metric findOne(Integer id);
	
}
