package com.flexreceipts.metrics.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MetricUnit implements Comparable<MetricUnit> {
	
	private Float value;
	
	@JsonCreator
	public MetricUnit (@JsonProperty("value") Float value) {
		this.value = value;
	}

	public Float getValue() {
		return value;
	}

	@Override
	public int compareTo(MetricUnit o) {
		if (value == o.value) {
			return 0;
		} else if (value > o.value) {
			return 1;
		} else {
			return -1;
		}
	}
	
}
