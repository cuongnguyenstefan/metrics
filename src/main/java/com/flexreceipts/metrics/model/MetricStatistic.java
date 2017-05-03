package com.flexreceipts.metrics.model;

public class MetricStatistic {
	
	private MetricUnit min;
	
	private MetricUnit max;
	
	private MetricUnit avg;
	
	private MetricUnit median;
	
	public MetricStatistic(MetricUnit min, MetricUnit max, MetricUnit avg, MetricUnit median) {
		this.min = min;
		this.max = max;
		this.avg = avg;
		this.median = median;
	}
	
	public MetricStatistic() {
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
	
}
