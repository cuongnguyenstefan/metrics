package com.flexreceipts.metrics.service;

import java.util.List;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.MetricStatistic;
import com.flexreceipts.metrics.model.MetricUnit;

public interface MetricService {
	
	Metric create(Metric metric);
	
	Metric addValues(int id, List<MetricUnit> values);
	
	MetricStatistic getStatistic(int id);
	
	Metric getMetric(int id);

}
