package com.flexreceipts.metrics.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.MetricUnit;
import com.flexreceipts.metrics.model.Statistic;
import com.flexreceipts.metrics.model.StatisticUnit;
import com.flexreceipts.metrics.service.FacadeMetricService;
import com.flexreceipts.metrics.service.MetricService;
import com.flexreceipts.metrics.service.StatisticService;

/**
 * High level Service in which controller call for services
 * 
 * @author Stefan
 *
 */
@Service
public class FacadeMetricServiceImpl implements FacadeMetricService {

	/**
	 * Metric's basic service
	 */
	@Autowired
	private MetricService metricService;

	/**
	 * Statistic's basic service
	 */
	@Autowired
	private StatisticService statisticService;

	/**
	 * Create a new metric, return null if bad input received
	 * 
	 * @param metric input metric
	 * @return newly saved metric
	 */
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

	/**
	 * Get metric of an existed metric, return null if bad input received
	 * 
	 * @param id existed metric's id
	 * @return requested metric
	 */
	@Override
	public Metric getMetric(int id) {
		return metricService.findOne(id);
	}

	/**
	 * Add new values to an existed metric, return null if bad input received
	 * 
	 * @param id existed metric's id
	 * @param metricUnits list of new values
	 * @return newly saved metric
	 */
	@Override
	public Metric addValues(int id, List<MetricUnit> metricUnits) {
		if ( metricUnits == null || metricUnits.isEmpty()) {
			return null;
		}
		Metric oldMetric = metricService.findOne(id);
		if (oldMetric == null) {
			return null;
		}
		oldMetric.getMetricUnits().addAll(metricUnits);
		Statistic statistic = statisticService.findOne(oldMetric.getStatistics());
		statistic.notify(oldMetric);
		statisticService.save(statistic);
		return metricService.save(oldMetric);
	}

	/**
	 * Get statistic of an existed metric, return null if bad input received
	 * 
	 * @param id existed metric's id
	 * @return statistic of that metric
	 */
	@Override
	public Statistic getStatisticsFromMetricId(int id) {
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
