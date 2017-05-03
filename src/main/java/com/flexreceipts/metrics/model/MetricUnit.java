package com.flexreceipts.metrics.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MetricUnit {

	private Float value;

	@JsonCreator
	public MetricUnit(@JsonProperty("value") Float value) {
		this.value = value;
	}

	public Float getValue() {
		return value;
	}

}
