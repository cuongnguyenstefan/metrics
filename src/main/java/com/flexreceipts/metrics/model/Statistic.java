package com.flexreceipts.metrics.model;

public interface Statistic {
	
	/**
	 * When a new metric unit added to the metric, the implemented statistic
	 * object needs to update its statistics also.
	 * @param newMetricUnit new added metric unit
	 * @return updated statistics
	 */
	Statistic update(MetricUnit newMetricUnit);
	
}
