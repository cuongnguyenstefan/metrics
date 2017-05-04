package com.flexreceipts.metrics.statisticobserver;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.Statistic;

/**
 * Abstract class for statistic calculating
 * 
 * @author Stefan
 *
 */
public abstract class StatisticObserver {
	
	/**
	 * Name of the observer
	 */
	private String name;
	
	/**
	 * Calculate the particular statistic
	 * 
	 * @param statistic statistic object that will be updated
	 * @param metric metric
	 */
	public abstract void calculate(Statistic statistic, Metric metric);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
