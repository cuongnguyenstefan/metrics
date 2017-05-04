package com.flexreceipts.metrics.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexreceipts.metrics.statisticobserver.StatisticObserver;

public class Statistic {

	private Integer id;

	private List<StatisticUnit> statistics;
	
	@JsonIgnore
	private Map<String, StatisticUnit> statisticUnits;

	@JsonIgnore
	private List<StatisticObserver> statisticObservers;

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
