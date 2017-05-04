package com.flexreceipts.metrics.statisticobserver;

import java.util.List;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.MetricUnit;
import com.flexreceipts.metrics.model.Statistic;
import com.flexreceipts.metrics.model.StatisticUnit;
import com.flexreceipts.metrics.util.Constant;

/**
 * Observer for calculating mean of the metric
 * 
 * @author Stefan
 *
 */
public class MeanStatisticObserver extends StatisticObserver {
	
	/**
	 * Construct the observer with a name
	 */
	public MeanStatisticObserver() {
		this.setName(Constant.MEAN);
	}

	/**
	 * Find Arithmetic Mean value from metric's units
	 * and update it into statistic's map
	 * Has O(n) runtime and O(1) space
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
		float current = 0;
		List<MetricUnit> metricUnits = metric.getMetricUnits();
		for (int i = 0; i < metricUnits.size(); i++) {
			current = current
					+ (metricUnits.get(i).getValue() - current) / (i+1);
		}
		StatisticUnit statisticUnit = new StatisticUnit(Constant.MEAN, current);
		statistic.getStatisticUnits().put(Constant.MEAN, statisticUnit);
	}


}
