package com.flexreceipts.metrics.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexreceipts.metrics.util.FLoatComparator;

public class Metric {

	private Integer id;

	private List<MetricUnit> metricUnits;

	@JsonIgnore
	private MetricStatistic metricStatistic;

	@JsonIgnore
	private PriorityQueue<Float> minHeap = new PriorityQueue<Float>();

	@JsonIgnore
	private PriorityQueue<Float> maxHeap = new PriorityQueue<Float>(new FLoatComparator());

	@JsonIgnore
	private int balance = 0;

	public void addMetricUnit(MetricUnit newUnit) {
		metricUnits.add(newUnit);
		updateMax(newUnit);
		updateMin(newUnit);
		updateAvg(newUnit);
		updateMedian(newUnit);
	}

	private void updateMax(MetricUnit newUnit) {
		if (metricStatistic.getMax() == null) {
			metricStatistic.setMax(newUnit);
			return;
		}
		if (metricStatistic.getMax().compareTo(newUnit) < 0) {
			metricStatistic.setMax(newUnit);
		}
	}

	private void updateMin(MetricUnit newUnit) {
		if (metricStatistic.getMin() == null) {
			metricStatistic.setMin(newUnit);
			return;
		}
		if (metricStatistic.getMin().compareTo(newUnit) > 0) {
			metricStatistic.setMin(newUnit);
		}
	}

	private void updateAvg(MetricUnit newUnit) {
		float current = metricStatistic.getAvg() == null ? 0 : metricStatistic.getAvg().getValue();
		current = current + (newUnit.getValue() - current) / metricUnits.size();
		metricStatistic.setAvg(new MetricUnit(current));
	}

	/**
	 * Update the current median when there is a new metric unit getting added
	 * into the metric.
	 * By using 2 heap, min heap and max heap, the algorithm keeps 2 heap balance
	 * at all the time where they either have the same amount of elements or one 
	 * more than the other.
	 * @param newUnit
	 */
	private void updateMedian(MetricUnit newUnit) {
		float newVal = newUnit.getValue();
		float currentMedian = metricStatistic.getMedian() == null ? 0 : metricStatistic.getMedian().getValue();
		switch (balance) {
		case 0: // the two heaps have the same number of elements
			if (newVal < currentMedian) {
				maxHeap.add(newVal);
				currentMedian = maxHeap.peek();
				balance = -1;
			} else {
				minHeap.add(newVal);
				currentMedian = minHeap.peek();
				balance = +1;
			}
			metricStatistic.setMedian(new MetricUnit(currentMedian));
			return;
		case +1:// minHeap has an extra element
			if (newVal <= currentMedian) {
				maxHeap.add(newVal);
			} else {
				minHeap.add(newVal);
				maxHeap.add(minHeap.poll());
			}
			balance = 0;
			return;
		case -1: // maxHeap has an extra element
			if (newVal >= currentMedian) {
				minHeap.add(newVal);
			} else {
				maxHeap.add(newVal);
				minHeap.add(maxHeap.poll());
			}
			balance = 0;
			return;
		}
	}

	public Metric() {
		this.metricUnits = new ArrayList<MetricUnit>();
		this.metricStatistic = new MetricStatistic();
	}

	public List<MetricUnit> getMetricUnits() {
		return Collections.unmodifiableList(metricUnits);
	}

	public MetricStatistic getMetricStatistic() {
		return metricStatistic;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
