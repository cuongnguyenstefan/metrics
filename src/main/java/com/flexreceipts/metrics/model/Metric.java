package com.flexreceipts.metrics.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Metric {

	private Integer id;

	private List<MetricUnit> metricUnits;

	@JsonIgnore
	private List<Statistic> statistics;

	public Metric() {
		this.metricUnits = new ArrayList<MetricUnit>();
		this.statistics = new ArrayList<Statistic>();
	}

	public void addMetricUnit(MetricUnit newUnit) {
		metricUnits.add(newUnit);
	}
	
	public void addStatistic(Statistic statistic) {
		statistics.add(statistic);
	}

	public List<MetricUnit> getMetricUnits() {
		return Collections.unmodifiableList(metricUnits);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<Statistic> getStatistics() {
		return statistics;
	}

	public void setStatistics(List<Statistic> statistics) {
		this.statistics = statistics;
	}

}
