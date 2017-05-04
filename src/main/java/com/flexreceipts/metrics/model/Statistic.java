package com.flexreceipts.metrics.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexreceipts.metrics.statisticobserver.StatisticObserver;

/**
 * Statistics information of a specific metric
 * 
 * @author Stefan
 *
 */
public class Statistic {

	/**
	 * ID of the statistic object
	 */
	private Integer id;

	/**
	 * List of statistic units that was calculated of that metric
	 * Is created from statisticUnits Map
	 */
	private List<StatisticUnit> statistics;
	
	/**
	 * Map of the unit name and value
	 * Is used to have an O(1) of getting a specific name of the statistics in order
	 * to calculate and update them 
	 */
	@JsonIgnore
	private Map<String, StatisticUnit> statisticUnits;

	/**
	 * List of statistic functions that is observing the current metric
	 */
	@JsonIgnore
	private List<StatisticObserver> statisticObservers;

	/**
	 * Construct the statistic object with a default empty map and list of observer
	 */
	public Statistic() {
		statisticUnits = new HashMap<String, StatisticUnit>();
		statisticObservers = new ArrayList<StatisticObserver>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<String, StatisticUnit> getStatisticUnits() {
		return statisticUnits;
	}

	public List<StatisticObserver> getStatisticObservers() {
		return statisticObservers;
	}

	public void setStatisticObservers(List<StatisticObserver> statisticObservers) {
		this.statisticObservers = statisticObservers;
	}
	
	public List<StatisticUnit> getStatistics() {
		return statistics;
	}

	public void setStatistics(List<StatisticUnit> statistics) {
		this.statistics = statistics;
	}

	public void attach(StatisticObserver statisticObserver) {
		this.statisticObservers.add(statisticObserver);
	}

	public void unattach(String observerName) {
		Iterator<StatisticObserver> iterator = this.statisticObservers.iterator();
		
		while (iterator.hasNext()) {
			StatisticObserver observer = iterator.next();
			if (observer.getName().equals(observerName)) {
				iterator.remove();
				break;
			}
		}
	}
	
	public void notify(Metric metric) {
		for (StatisticObserver observer : statisticObservers) {
			observer.calculate(this, metric);
		}
	}

}
