package com.flexreceipts.metrics.model;

public class StatisticUnit {
	
	private String name;
	
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