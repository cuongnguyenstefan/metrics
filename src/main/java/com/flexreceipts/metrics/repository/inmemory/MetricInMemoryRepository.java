package com.flexreceipts.metrics.repository.inmemory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.repository.MetricRepository;

@Repository
public class MetricInMemoryRepository implements MetricRepository {
	
	private Map<Integer, Metric> data = new HashMap<Integer, Metric>();
	
	private int currentIdx = 1;

	@Override
	public Metric save(Metric entity) {
		if (entity.getId() == null) {
			entity.setId(currentIdx);
			currentIdx++;
		}
		data.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public Metric findOne(Integer id) {
		return data.get(id);
	}

}
