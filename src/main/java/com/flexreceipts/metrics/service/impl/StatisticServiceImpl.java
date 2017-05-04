package com.flexreceipts.metrics.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexreceipts.metrics.model.Statistic;
import com.flexreceipts.metrics.repository.StatisticRepository;
import com.flexreceipts.metrics.service.StatisticService;
import com.flexreceipts.metrics.statisticobserver.MaxStatisticObserver;
import com.flexreceipts.metrics.statisticobserver.MeanStatisticObserver;
import com.flexreceipts.metrics.statisticobserver.MedianStatisticObserver;
import com.flexreceipts.metrics.statisticobserver.MinStatisticObserver;

/**
 * Basic service of statistic
 * 
 * @author Stefan
 *
 */
@Service
public class StatisticServiceImpl implements StatisticService {

	/**
	 * Repository of Statistic
	 */
	@Autowired
	private StatisticRepository statisticRepository;

	/**
	 * Create a new statistic with basic observers for calculating min, max,
	 * mean and median
	 * 
	 * @return newly created statistic
	 */
	@Override
	public Statistic create() {
		Statistic statistic = new Statistic();
		MaxStatisticObserver maxStatisticObserver = new MaxStatisticObserver();
		MeanStatisticObserver meanStatisticObserver = new MeanStatisticObserver();
		MinStatisticObserver minStatisticObserver = new MinStatisticObserver();
		MedianStatisticObserver medianStatisticObserver = new MedianStatisticObserver();
		statistic.attach(maxStatisticObserver);
		statistic.attach(meanStatisticObserver);
		statistic.attach(minStatisticObserver);
		statistic.attach(medianStatisticObserver);
		return statisticRepository.save(statistic);
	}

	/**
	 * Find a statistic based on id, return null if not found
	 * 
	 * @param statisticId id
	 * @return requested statistic
	 */
	@Override
	public Statistic findOne(Integer statisticId) {
		return statisticRepository.findOne(statisticId);
	}

	/**
	 * Save a statistic, return null if input has no id
	 * 
	 * @param statistic statistic
	 * @return saved statistic
	 */
	@Override
	public Statistic save(Statistic statistic) {
		if (statistic == null || statistic.getId() == null || statistic.getId() == 0) {
			return null;
		}
		return statisticRepository.save(statistic);
	}

	
}
