package com.flexreceipts.metrics.service;

import java.util.List;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.MetricUnit;
import com.flexreceipts.metrics.model.Statistic;

/**
 * High level Service in which controller call for services
 * 
 * @author Stefan
 *
 */
public interface FacadeMetricService {
	
	/**
	 * Create a new metric, return null if bad input received
	 * 
	 * @param metric input metric
	 * @return newly saved metric
	 */
	Metric create(Metric metric);
	
	/**
	 * Add new values to an existed metric, return null if bad input received
	 * 
	 * @param id existed metric's id
	 * @param metricUnits list of new values
	 * @return newly saved metric
	 */
	Metric addValues(int id, List<MetricUnit> metricUnits);
	
	/**
	 * Get statistic of an existed metric, return null if bad input received
	 * 
	 * @param id existed metric's id
	 * @return statistic of that metric
	 */
	Statistic getStatisticsFromMetricId(int id);
	
	/**
	 * Get metric of an existed metric, return null if bad input received
	 * 
	 * @param id existed metric's id
	 * @return requested metric
	 */
	Metric getMetric(int id);

}
