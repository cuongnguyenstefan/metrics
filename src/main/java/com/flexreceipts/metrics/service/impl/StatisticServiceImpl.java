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

@Service
public class StatisticServiceImpl implements StatisticService {

	@Autowired
	private StatisticRepository statisticRepository;

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

	@Override
	public Statistic findOne(Integer statisticId) {
		return statisticRepository.findOne(statisticId);
	}

	@Override
	public Statistic save(Statistic statistic) {
		if (statistic.getId() == null || statistic.getId() == 0) {
			return null;
		}
		return statisticRepository.save(statistic);
	}

	
}
