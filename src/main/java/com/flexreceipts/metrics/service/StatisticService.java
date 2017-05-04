package com.flexreceipts.metrics.service;

import com.flexreceipts.metrics.model.Statistic;

/**
 * Basic service of statistic
 * 
 * @author Stefan
 *
 */
public interface StatisticService {

	/**
	 * Create a new statistic with basic observers for calculating min, max,
	 * mean and median
	 * 
	 * @return newly created statistic
	 */
	Statistic create();

	/**
	 * Save a statistic, return null if input has no id
	 * 
	 * @param statistic statistic
	 * @return saved statistic
	 */
	Statistic save(Statistic statistic);

	/**
	 * Find a statistic based on id, return null if not found
	 * 
	 * @param statisticId id
	 * @return requested statistic
	 */
	Statistic findOne(Integer statisticId);

}
