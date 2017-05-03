package com.flexreceipts.metrics.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Metric {

	private Integer id;

	private List<MetricUnit> metricUnits;

	@JsonIgnore
	private Integer statistics;

	public Metric() {
		this.metricUnits = new ArrayList<MetricUnit>();
	}

	public List<MetricUnit> getMetricUnits() {
		return metricUnits;
	}

	public void setMetricUnits(List<MetricUnit> metricUnits) {
		this.metricUnits = metricUnits;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatistics() {
		return statistics;
	}

	public void setStatistics(Integer statistics) {
		this.statistics = statistics;
	}

}
