package com.flexreceipts.metrics.model;

/**
 * Wrapper of statistic unit, contain key-value pair of the statistic
 * 
 * @author Stefan
 *
 */
public class StatisticUnit {
	
	/**
	 * Name of the statistic
	 */
	private String name;
	
	/**
	 * Value of the statistic
	 */
	private Float value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public StatisticUnit(String name, Float value) {
		this.name = name;
		this.value = value;
	}

	
}
