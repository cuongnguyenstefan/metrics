package com.flexreceipts.metrics.statisticobserver;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.MetricUnit;
import com.flexreceipts.metrics.model.Statistic;
import com.flexreceipts.metrics.model.StatisticUnit;
import com.flexreceipts.metrics.util.Constant;

/**
 * Max calculating observer
 * 
 * @author Stefan
 *
 */
public class MinStatisticObserver extends StatisticObserver {
	
	/**
	 * Construct the observer with a name
	 */
	public MinStatisticObserver() {
		this.setName(Constant.MIN);
	}

	/**
	 * Find Min value from metric's units and update it into statistic's map
	 * Has O(n) of run time and O(1) of space
	 * 
	 * @param statistic statistic object that will be updated
	 * @param metric metric
	 */
	@Override
	public void calculate(Statistic statistic, Metric metric) {
		if (metric == null || statistic == null || metric.getMetricUnits() == null
				|| metric.getMetricUnits().isEmpty()) {
			return;
		}
		Float min = null;
		for (MetricUnit metricUnit : metric.getMetricUnits()) {
			if (min == null) {
				min = metricUnit.getValue();
			}
			if (min > metricUnit.getValue()) {
				min = metricUnit.getValue();
			}
		}
		statistic.getStatisticUnits().put(Constant.MIN, new StatisticUnit(Constant.MIN, min));
	}

}
