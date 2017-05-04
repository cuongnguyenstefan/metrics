package com.flexreceipts.metrics.repository;

import com.flexreceipts.metrics.model.Metric;

/**
 * Metric repository
 * 
 * @author Stefan
 *
 */
public interface MetricRepository {
	
	/**
	 * Save/create a metric
	 * 
	 * @param entity metric
	 * @return newly saved/created metric
	 */
	Metric save(Metric entity);
	
	/**
	 * Find a metric
	 * 
	 * @param id id of the metric
	 * @return if found, return the metric
	 * if not found, return null
	 */
	Metric findOne(Integer id);
	
}
