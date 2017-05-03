package com.flexreceipts.metrics.repository;

import com.flexreceipts.metrics.model.Statistic;

public interface StatisticRepository {

	Statistic save(Statistic entity);
	
	Statistic findOne(Integer id);
	
}
