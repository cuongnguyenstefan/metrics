package com.flexreceipts.metrics.service;

import com.flexreceipts.metrics.model.Metric;

/**
 * Basic service of metric
 * 
 * @author Stefan
 *
 */
public interface MetricService {
	
	/**
	 * Create a new metric, return null if input is null or metric has no name
	 * 
	 * @param metric metric
	 * @return newly created metric
	 */
	Metric create(Metric metric);
	
	/**
	 * Find a metric from an input id, return null if not found
	 * 
	 * @param id metric's id
	 * @return requested metric
	 */
	Metric findOne(int id);
	
	/**
	 * Save the current metric, return null if metric has no id
	 * 
	 * @param metric metric
	 * @return saved metric
	 */
	Metric save(Metric metric);

}
