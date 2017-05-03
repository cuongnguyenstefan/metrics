package com.flexreceipts.metrics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.MetricUnit;
import com.flexreceipts.metrics.model.Statistic;
import com.flexreceipts.metrics.repository.MetricRepository;
import com.flexreceipts.metrics.service.MetricService;
import com.flexreceipts.metrics.service.StatisticService;

@Service
public class MetricServiceImpl implements MetricService {

	@Autowired
	private MetricRepository metricRepository;
	
	@Autowired
	private StatisticService statisticService;

	@Override
	public Metric create(Metric metric) {
		if (metric == null || metric.getMetricUnits() == null) {
			return null;
		}

		Statistic basicStatistic = statisticService.create(metric);

		metric.setStatistics(basicStatistic.getId());

		return metricRepository.save(metric);
	}

	@Override
	public Metric addValues(int id, List<MetricUnit> values) {
		if (values == null || values.isEmpty()) {
			return null;
		}
		Metric metric = metricRepository.findOne(id);
		if (metric == null) {
			return null;
		}
		statisticService.addAll(metric.getStatistics(), values);
		metric.getMetricUnits().addAll(values);
		return metricRepository.save(metric);
	}

	@Override
	public Statistic getStatistics(int id) {
		Metric metric = metricRepository.findOne(id);
		if (metric == null) {
			return null;
		}
		return statisticService.findOne(metric.getStatistics());
	}

	@Override
	public Metric getMetric(int id) {
		return metricRepository.findOne(id);
	}


}
