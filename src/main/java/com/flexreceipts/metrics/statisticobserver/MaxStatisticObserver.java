package com.flexreceipts.metrics.statisticobserver;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.MetricUnit;
import com.flexreceipts.metrics.model.Statistic;
import com.flexreceipts.metrics.model.StatisticUnit;
import com.flexreceipts.metrics.util.Constant;

public class MaxStatisticObserver extends StatisticObserver {

	@Override
	public void calculate(Statistic statistic, Metric metric) {
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
