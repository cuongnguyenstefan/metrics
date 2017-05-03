package com.flexreceipts.metrics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.MetricUnit;
import com.flexreceipts.metrics.model.Statistic;
import com.flexreceipts.metrics.repository.StatisticRepository;
import com.flexreceipts.metrics.service.StatisticService;

@Service
public class StatisticServiceImpl implements StatisticService {

	@Autowired
	private StatisticRepository statisticRepository;

	@Override
	public Statistic create(Metric metric) {
		Statistic statistic = new Statistic();
		updateStatistic(statistic, metric.getMetricUnits());
		return statisticRepository.save(statistic);
	}

	@Override
	public Statistic addAll(Integer statisticId, List<MetricUnit> metricUnits) {
		Statistic statistic = statisticRepository.findOne(statisticId);
		updateStatistic(statistic, metricUnits);
		return statisticRepository.save(statistic);
	}

	@Override
	public Statistic findOne(Integer statisticId) {
		return statisticRepository.findOne(statisticId);
	}

	public void updateStatistic(Statistic statistic, List<MetricUnit> metricUnits) {
		for (MetricUnit newMetricUnit : metricUnits) {
			updateMax(statistic, newMetricUnit);
			updateMin(statistic, newMetricUnit);
			updateMedian(statistic, newMetricUnit);
			updateAvg(statistic, newMetricUnit);
		}
	}

	private void updateMax(Statistic statistic, MetricUnit newUnit) {
		if (statistic.getMax() == null) {
			statistic.setMax(newUnit);
			return;
		}
		if (statistic.getMax().compareTo(newUnit) < 0) {
			statistic.setMax(newUnit);
		}
	}

	private void updateMin(Statistic statistic, MetricUnit newUnit) {
		if (statistic.getMin() == null) {
			statistic.setMin(newUnit);
			return;
		}
		if (statistic.getMin().compareTo(newUnit) > 0) {
			statistic.setMin(newUnit);
		}
	}

	private void updateAvg(Statistic statistic, MetricUnit newUnit) {
		float current = statistic.getAvg() == null ? 0 : statistic.getAvg().getValue();
		current = current
				+ (newUnit.getValue() - current) / (statistic.getMinHeap().size() + statistic.getMaxHeap().size());
		statistic.setAvg(new MetricUnit(current));
	}

	/**
	 * Update the current median when there is a new metric unit getting added
	 * into the metric. By using 2 heap, min heap and max heap, the algorithm
	 * keeps 2 heap balance at all the time where they either have the same
	 * amount of elements or one more than the other.
	 * 
	 * @param statistic 
	 * @param newUnit
	 */
	private void updateMedian(Statistic statistic, MetricUnit newUnit) {
		float newVal = newUnit.getValue();
		float currentMedian = statistic.getMedian() == null ? 0 : statistic.getMedian().getValue();
		// 2 heap has same size
		if (statistic.getMinHeap().size() == statistic.getMaxHeap().size()) {
			if (newVal < currentMedian) {
				statistic.getMaxHeap().add(newVal);
				currentMedian = statistic.getMaxHeap().peek();
			} else {
				statistic.getMinHeap().add(newVal);
				currentMedian = statistic.getMinHeap().peek();
			}
			statistic.setMedian(new MetricUnit(currentMedian));
		} else if (statistic.getMinHeap().size() > statistic.getMaxHeap().size()) { 
			// minHeap has an extra element
			
			if (newVal <= currentMedian) {
				statistic.getMaxHeap().add(newVal);
			} else {
				statistic.getMinHeap().add(newVal);
				statistic.getMaxHeap().add(statistic.getMinHeap().poll());
			}
		} else { // maxHeap has an extra element
			if (newVal >= currentMedian) {
				statistic.getMinHeap().add(newVal);
			} else {
				statistic.getMaxHeap().add(newVal);
				statistic.getMinHeap().add(statistic.getMaxHeap().poll());
			}
		}
	}
}
