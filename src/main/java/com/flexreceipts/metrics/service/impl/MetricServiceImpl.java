package com.flexreceipts.metrics.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.repository.MetricRepository;
import com.flexreceipts.metrics.service.MetricService;

@Service
public class MetricServiceImpl implements MetricService {

	@Autowired
	private MetricRepository metricRepository;

	@Override
	public Metric create(Metric metric) {
		if (metric == null || metric.getId() != null || metric.getName() == null) {
			return null;
		}

		return metricRepository.save(metric);
	}

	@Override
	public Metric findOne(int id) {
		return metricRepository.findOne(id);
	}

	@Override
	public Metric save(Metric metric) {
		if (metric.getId() == null || metric.getId() == 0) {
			return null;
		}
		return metricRepository.save(metric);
	}

}
