package com.flexreceipts.metrics.repository;

import com.flexreceipts.metrics.model.Statistic;

/**
 * Statistic Repository
 * 
 * @author Stefan
 *
 */
public interface StatisticRepository {

	/**
	 * Save/create a statistic
	 * 
	 * @param entity statistic
	 * @return newly saved/created statistic
	 */
	Statistic save(Statistic entity);
	
	/**
	 * Find a statistic
	 * 
	 * @param id id of the statistic
	 * @return if found, return the statistic
	 * if not found, return null
	 */
	Statistic findOne(Integer id);
	
}
