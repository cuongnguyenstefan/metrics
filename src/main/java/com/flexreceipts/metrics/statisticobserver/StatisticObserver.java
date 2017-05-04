package com.flexreceipts.metrics.statisticobserver;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.Statistic;

public abstract class StatisticObserver {
	
	private String name;
	
	public abstract void calculate(Statistic statistic, Metric metric);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
