package com.flexreceipts.metrics.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Wrapper class for the value of each unit in metric
 * Currently include value of the unit
 * Can be extended to save other information of a unit
 * 
 * @author Stefan
 *
 */
public class MetricUnit {
	
	/**
	 * Value of the unit
	 */
	private Float value;
	
	@JsonCreator
	public MetricUnit (@JsonProperty("value") Float value) {
		this.value = value;
	}

	public Float getValue() {
		return value;
	}

	
}
