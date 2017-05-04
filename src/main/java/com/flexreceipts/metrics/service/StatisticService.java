package com.flexreceipts.metrics.service;

import com.flexreceipts.metrics.model.Statistic;

public interface StatisticService {
	
	Statistic create();
	
	Statistic save(Statistic statistic);
	
	Statistic findOne(Integer statisticId);

}
