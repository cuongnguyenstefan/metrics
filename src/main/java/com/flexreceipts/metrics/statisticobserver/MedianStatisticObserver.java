package com.flexreceipts.metrics.statisticobserver;

import java.util.PriorityQueue;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.MetricUnit;
import com.flexreceipts.metrics.model.Statistic;
import com.flexreceipts.metrics.model.StatisticUnit;
import com.flexreceipts.metrics.util.Constant;
import com.flexreceipts.metrics.util.FLoatReverseComparator;

/**
 * Observer for Statistic object, it is used to create/update the median value
 * 
 * 
 * @author Stefan
 *
 */
public class MedianStatisticObserver extends StatisticObserver {
	
	/**
	 * Store bigger half of elements
	 * 
	 */
	private PriorityQueue<Float> minHeap;
	
	/**
	 * Store lesser half of elements
	 * 
	 */
	private PriorityQueue<Float> maxHeap;
	
	
	/**
	 * Construct the observer with default queues for 2 heaps
	 * 
	 */
	public MedianStatisticObserver() {
		this.minHeap = new PriorityQueue<Float>();
		this.maxHeap = new PriorityQueue<Float>(new FLoatReverseComparator());
	}

	/**
	 * Calculate the median from metric's units and update into Statistic's map
	 * 
	 * @param statistic statistic object that will be updated
	 * @param metric include the units that will be check to find median
	 * 
	 */
	@Override
	public void calculate(Statistic statistic, Metric metric) {
		float currentMedian = 0;
		for (MetricUnit metricUnit : metric.getMetricUnits()) {
			currentMedian = updateMedian(currentMedian, metricUnit);
		}
		StatisticUnit statisticUnit = new StatisticUnit(Constant.MEDIAN, currentMedian);
		statistic.getStatisticUnits().put(Constant.MEDIAN, statisticUnit);
	}

	/**
	 * Update the current median when there is a new metric unit getting added
	 * into the metric. By using 2 heap, min heap and max heap, the algorithm
	 * keeps 2 heap balance at all the time where they either have the same
	 * amount of elements or one more than the other. A new element 
	 * is added in suitable heap, then for the next operation, it will be
	 * balanced out
	 * 
	 * @param currentMedian current Median of the operation
	 * @param newUnit next element in line to be added
	 * @return new median
	 * 
	 */
	private float updateMedian(float currentMedian, MetricUnit newUnit) {
		float newVal = newUnit.getValue();
		// 2 heap has same size
		if (minHeap.size() == maxHeap.size()) {
			if (newVal < currentMedian) {
				maxHeap.add(newVal);
				currentMedian = maxHeap.peek();
			} else {
				minHeap.add(newVal);
				currentMedian = minHeap.peek();
			}
		} else if (minHeap.size() > maxHeap.size()) {
			// minHeap has an extra element

			if (newVal <= currentMedian) {
				maxHeap.add(newVal);
			} else {
				minHeap.add(newVal);
				maxHeap.add(minHeap.poll());
			}
		} else { // maxHeap has an extra element
			if (newVal >= currentMedian) {
				minHeap.add(newVal);
			} else {
				maxHeap.add(newVal);
				minHeap.add(maxHeap.poll());
			}
		}
		return currentMedian;
	}

}
