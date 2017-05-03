package com.flexreceipts.metrics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.BasicStatistic;
import com.flexreceipts.metrics.model.MetricUnit;
import com.flexreceipts.metrics.model.Statistic;
import com.flexreceipts.metrics.repository.MetricRepository;
import com.flexreceipts.metrics.service.MetricService;

@Service
public class MetricServiceImpl implements MetricService {

	@Autowired
	private MetricRepository metricRepository;

	@Override
	public Metric create(Metric metric) {
		if (metric == null || metric.getMetricUnits() == null) {
			return null;
		}

		Metric m = new Metric();
		BasicStatistic basicStatistic = new BasicStatistic();

		m.addStatistic(basicStatistic);
		boolean addMetricUnits = addMetricUnits(m, metric.getMetricUnits());
		if (!addMetricUnits) {
			return null;
		}

		return metricRepository.save(m);
	}

	@Override
	public Metric addValues(int id, List<MetricUnit> values) {
		Metric metric = metricRepository.findOne(id);
		if (metric == null) {
			return null;
		}
		boolean addMetricUnits = addMetricUnits(metric, values);
		if (!addMetricUnits) {
			return null;
		}
		return metricRepository.save(metric);
	}

	@Override
	public List<Statistic> getStatistics(int id) {
		Metric metric = metricRepository.findOne(id);
		if (metric == null) {
			return null;
		}
		return metric.getStatistics();
	}

	@Override
	public Metric getMetric(int id) {
		return metricRepository.findOne(id);
	}

	private boolean addMetricUnits(Metric metric, List<MetricUnit> metricUnits) {
		for (MetricUnit mu : metricUnits) {
			metric.addMetricUnit(mu);
			for (Statistic statistic : metric.getStatistics()) {
				statistic.update(mu);
			}
		}
		return true;
	}

}
