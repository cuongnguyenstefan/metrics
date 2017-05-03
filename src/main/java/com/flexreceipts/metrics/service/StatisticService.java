package com.flexreceipts.metrics.service;

import java.util.List;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.MetricUnit;
import com.flexreceipts.metrics.model.Statistic;

public interface StatisticService {
	
	Statistic create(Metric metric);
	
	Statistic addAll(Integer statisticId, List<MetricUnit> metricUnits);
	
	Statistic findOne(Integer statisticId);

}
