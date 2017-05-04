package com.flexreceipts.metrics.statisticobserver;

import java.util.List;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.MetricUnit;
import com.flexreceipts.metrics.model.Statistic;
import com.flexreceipts.metrics.model.StatisticUnit;
import com.flexreceipts.metrics.util.Constant;

public class MeanStatisticObserver extends StatisticObserver {

	@Override
	public void calculate(Statistic statistic, Metric metric) {
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
