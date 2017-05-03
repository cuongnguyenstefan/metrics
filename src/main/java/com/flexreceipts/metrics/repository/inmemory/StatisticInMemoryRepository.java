package com.flexreceipts.metrics.repository.inmemory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flexreceipts.metrics.model.Statistic;
import com.flexreceipts.metrics.repository.StatisticRepository;

@Repository
public class StatisticInMemoryRepository implements StatisticRepository {

	private Map<Integer, Statistic> data = new HashMap<Integer, Statistic>();

	private int currentIdx = 1;

	@Override
	public Statistic save(Statistic entity) {
		if (entity.getId() == null) {
			entity.setId(currentIdx);
			currentIdx++;
		}
		data.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public Statistic findOne(Integer id) {
		return data.get(id);
	}

}
