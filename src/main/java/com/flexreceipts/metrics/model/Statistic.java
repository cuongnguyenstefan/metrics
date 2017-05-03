package com.flexreceipts.metrics.model;

import java.util.PriorityQueue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexreceipts.metrics.util.FLoatReverseComparator;

public class Statistic {

	private Integer id;

	private MetricUnit min;

	private MetricUnit max;

	private MetricUnit avg;

	private MetricUnit median;

	@JsonIgnore
	private PriorityQueue<Float> minHeap;

	@JsonIgnore
	private PriorityQueue<Float> maxHeap;

	public Statistic() {
		this.minHeap = new PriorityQueue<Float>();
		this.maxHeap = new PriorityQueue<Float>(new FLoatReverseComparator());
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PriorityQueue<Float> getMinHeap() {
		return minHeap;
	}

	public void setMinHeap(PriorityQueue<Float> minHeap) {
		this.minHeap = minHeap;
	}

	public PriorityQueue<Float> getMaxHeap() {
		return maxHeap;
	}

	public void setMaxHeap(PriorityQueue<Float> maxHeap) {
		this.maxHeap = maxHeap;
	}

}
