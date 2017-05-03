package com.flexreceipts.metrics.util;

import java.util.Comparator;

import com.flexreceipts.metrics.model.MetricUnit;

public class MetricMaxComparator implements Comparator<MetricUnit> {

	@Override
	public int compare(MetricUnit o1, MetricUnit o2) {
		return o2.getValue().intValue() - o1.getValue().intValue();
	}

}
