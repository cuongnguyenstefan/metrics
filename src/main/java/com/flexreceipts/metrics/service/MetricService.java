package com.flexreceipts.metrics.service;

import java.util.List;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.MetricUnit;
import com.flexreceipts.metrics.model.Statistic;

public interface MetricService {
	
	Metric create(Metric metric);
	
	Metric addValues(int id, List<MetricUnit> values);
	
	List<Statistic> getStatistics(int id);
	
	Metric getMetric(int id);

}
