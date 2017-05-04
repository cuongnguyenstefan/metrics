package com.flexreceipts.metrics.statisticobserver;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.MetricUnit;
import com.flexreceipts.metrics.model.Statistic;
import com.flexreceipts.metrics.model.StatisticUnit;
import com.flexreceipts.metrics.util.Constant;

public class MinStatisticObserver extends StatisticObserver {

	@Override
	public void calculate(Statistic statistic, Metric metric) {
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
