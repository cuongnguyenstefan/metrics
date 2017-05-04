package com.flexreceipts.metrics.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.Statistic;
import com.flexreceipts.metrics.model.StatisticUnit;
import com.flexreceipts.metrics.service.FacadeMetricService;
import com.flexreceipts.metrics.service.MetricService;
import com.flexreceipts.metrics.service.StatisticService;

@Service
public class FacadeMetricServiceImpl implements FacadeMetricService {

	@Autowired
	private MetricService metricService;

	@Autowired
	private StatisticService statisticService;

	@Override
	public Metric create(Metric metric) {
		if (metric.getName() == null || "".equals(metric.getName())) {
			return null;
		}
		Statistic statistic = statisticService.create();
		metric.setStatistics(statistic.getId());
		metric.setId(null);
		if (metric.getMetricUnits() != null && !metric.getMetricUnits().isEmpty()) {
			statistic.notify(metric);
			statisticService.save(statistic);
		}
		return metricService.create(metric);
	}

	@Override
	public Metric getMetric(int id) {
		return metricService.findOne(id);
	}

	@Override
	public Metric addValues(int id, Metric metric) {
		if (metric == null || metric.getMetricUnits() == null || metric.getMetricUnits().isEmpty()) {
			return null;
		}
		Metric oldMetric = metricService.findOne(id);
		oldMetric.getMetricUnits().addAll(metric.getMetricUnits());
		if (metric.getName() != null && "".equals(metric.getName())) {
			oldMetric.setName(metric.getName());
		}
		if (metric.getDescription() != null && "".equals(metric.getDescription())) {
			oldMetric.setDescription(metric.getDescription());
		}
		Statistic statistic = statisticService.findOne(oldMetric.getStatistics());
		statistic.notify(oldMetric);
		statisticService.save(statistic);
		return metricService.save(oldMetric);
	}

	@Override
	public Statistic getStatistics(int id) {
		Metric metric = metricService.findOne(id);
		if (metric == null) {
			return null;
		}
		Statistic findOne = statisticService.findOne(metric.getStatistics());
		List<StatisticUnit> statistics = new ArrayList<StatisticUnit>();
		statistics.addAll(findOne.getStatisticUnits().values());
		findOne.setStatistics(statistics);
		return findOne;
	}

}
