package com.flexreceipts.metrics.model;

import java.util.PriorityQueue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexreceipts.metrics.util.FLoatReverseComparator;

public class BasicStatistic implements Statistic {
	
	private MetricUnit min;
	
	private MetricUnit max;
	
	private MetricUnit avg;
	
	private MetricUnit median;
	
	@JsonIgnore
	private PriorityQueue<Float> minHeap = new PriorityQueue<Float>();

	@JsonIgnore
	private PriorityQueue<Float> maxHeap = new PriorityQueue<Float>(new FLoatReverseComparator());

	@JsonIgnore
	private int balance = 0;
	
	public BasicStatistic(MetricUnit min, MetricUnit max, MetricUnit avg, MetricUnit median) {
		this.min = min;
		this.max = max;
		this.avg = avg;
		this.median = median;
	}
	
	public BasicStatistic() {
	}

	public MetricUnit getMin() {
		return min;
	}

	public void setMin(MetricUnit min) {
		this.min = min;
	}

	public MetricUnit getMax() {
		return max;
	}

	public void setMax(MetricUnit max) {
		this.max = max;
	}

	public MetricUnit getAvg() {
		return avg;
	}

	public void setAvg(MetricUnit avg) {
		this.avg = avg;
	}

	public MetricUnit getMedian() {
		return median;
	}

	public void setMedian(MetricUnit median) {
		this.median = median;
	}

	@Override
	public Statistic update(MetricUnit newMetricUnit) {
		updateMax(newMetricUnit);
		updateMin(newMetricUnit);
		updateAvg(newMetricUnit);
		updateMedian(newMetricUnit);
		return this;
	}
	
	private void updateMax(MetricUnit newUnit) {
		if (getMax() == null) {
			setMax(newUnit);
			return;
		}
		if (getMax().compareTo(newUnit) < 0) {
			setMax(newUnit);
		}
	}

	private void updateMin(MetricUnit newUnit) {
		if (getMin() == null) {
			setMin(newUnit);
			return;
		}
		if (getMin().compareTo(newUnit) > 0) {
			setMin(newUnit);
		}
	}

	private void updateAvg(MetricUnit newUnit) {
		float current = getAvg() == null ? 0 : getAvg().getValue();
		current = current + (newUnit.getValue() - current) / (minHeap.size() + maxHeap.size());
		setAvg(new MetricUnit(current));
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
		float currentMedian = median == null ? 0 : median.getValue();
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
			setMedian(new MetricUnit(currentMedian));
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
	
}
