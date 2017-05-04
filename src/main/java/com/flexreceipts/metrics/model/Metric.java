package com.flexreceipts.metrics.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Metric object
 * 
 * @author Stefan
 *
 */
public class Metric {

	/**
	 * ID of the metric
	 */
	private Integer id;

	/**
	 * Name of the metric
	 */
	private String name;

	/**
	 * Description of the metric
	 */
	private String description;

	/**
	 * Units in the metric
	 */
	private List<MetricUnit> metricUnits;

	/**
	 * ID of the statistic object relating to the current metric
	 */
	@JsonIgnore
	private Integer statistics;

	/**
	 * Construct the metric with a default new metric unit list
	 */
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
