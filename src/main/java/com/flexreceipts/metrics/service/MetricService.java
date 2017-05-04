package com.flexreceipts.metrics.service;

import com.flexreceipts.metrics.model.Metric;

public interface MetricService {
	
	Metric create(Metric metric);
	
	Metric findOne(int id);
	
	Metric save(Metric metric);

}
