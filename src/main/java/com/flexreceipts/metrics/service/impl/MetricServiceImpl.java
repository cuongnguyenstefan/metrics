package com.flexreceipts.metrics.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.repository.MetricRepository;
import com.flexreceipts.metrics.service.MetricService;

/**
 * Basic service of metric
 * 
 * @author Stefan
 *
 */
@Service
public class MetricServiceImpl implements MetricService {

	/**
	 * Repository of metric
	 */
	@Autowired
	private MetricRepository metricRepository;

	/**
	 * Create a new metric, return null if input is null or metric has no name
	 * 
	 * @param metric metric
	 * @return newly created metric
	 */
	@Override
	public Metric create(Metric metric) {
		if (metric == null || metric.getId() != null || metric.getName() == null) {
			return null;
		}

		return metricRepository.save(metric);
	}

	/**
	 * Find a metric from an input id, return null if not found
	 * 
	 * @param id metric's id
	 * @return requested metric
	 */
	@Override
	public Metric findOne(int id) {
		return metricRepository.findOne(id);
	}

	/**
	 * Save the current metric, return null if metric has no id
	 * 
	 * @param metric metric
	 * @return saved metric
	 */
	@Override
	public Metric save(Metric metric) {
		if (metric == null || metric.getId() == null || metric.getId() == 0) {
			return null;
		}
		return metricRepository.save(metric);
	}

}
