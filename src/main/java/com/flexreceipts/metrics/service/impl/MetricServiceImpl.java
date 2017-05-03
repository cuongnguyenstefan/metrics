package com.flexreceipts.metrics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.MetricStatistic;
import com.flexreceipts.metrics.model.MetricUnit;
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
		for (MetricUnit mu : metric.getMetricUnits()) {
			m.addMetricUnit(mu);
		}
		return metricRepository.save(m);
	}

	@Override
	public Metric addValues(int id, List<MetricUnit> values) {
		Metric metric = metricRepository.findOne(id);
		if (metric == null) {
			return null;
		}
		for (MetricUnit value : values) {
			metric.addMetricUnit(value);
		}
		return metricRepository.save(metric);
	}

	@Override
	public MetricStatistic getStatistic(int id) {
		Metric metric = metricRepository.findOne(id);
		if (metric == null) {
			return null;
		}
		return metric.getMetricStatistic();
	}

	@Override
	public Metric getMetric(int id) {
		return metricRepository.findOne(id);
	}

}
