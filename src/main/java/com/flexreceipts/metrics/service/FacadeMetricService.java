package com.flexreceipts.metrics.service;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.Statistic;

public interface FacadeMetricService {
	
	Metric create(Metric metric);
	
	Metric addValues(int id, Metric metric);
	
	Statistic getStatistics(int id);
	
	Metric getMetric(int id);

}
