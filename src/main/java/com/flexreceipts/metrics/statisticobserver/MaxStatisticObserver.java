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
public class MaxStatisticObserver extends StatisticObserver {

	/**
	 * Construct the observer with a name
	 */
	public MaxStatisticObserver() {
		this.setName(Constant.MAX);
	}

	/**
	 * Find Max value from metric's units and update it into statistic's map Has
	 * O(n) of run time and O(1) of space
	 * 
	 * @param statistic
	 *            statistic object that will be updated
	 * @param metric
	 *            metric
	 */
	@Override
	public void calculate(Statistic statistic, Metric metric) {
		if (metric == null || statistic == null || metric.getMetricUnits() == null
				|| metric.getMetricUnits().isEmpty()) {
			return;
		}
		Float max = null;
		for (MetricUnit newMetricUnit : metric.getMetricUnits()) {
			if (max == null) {
				max = newMetricUnit.getValue();
			}
			if (max < newMetricUnit.getValue()) {
				max = newMetricUnit.getValue();
			}
		}
		statistic.getStatisticUnits().put(Constant.MAX, new StatisticUnit(Constant.MAX, max));
	}

}
