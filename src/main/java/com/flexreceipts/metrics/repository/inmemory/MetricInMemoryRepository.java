package com.flexreceipts.metrics.repository.inmemory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.repository.MetricRepository;

/**
 * Repository of Metrics, which is implementing an in-memory solution
 * 
 * @author Stefan
 *
 */
@Repository
public class MetricInMemoryRepository implements MetricRepository {
	
	/**
	 * Metrics, store by map with id as a key
	 * Giving save and find an O(1) runtime
	 * 
	 */
	private Map<Integer, Metric> data = new HashMap<Integer, Metric>();
	
	/**
	 * ID of the next metric
	 */
	private int currentIdx = 1;

	/**
	 * Save/create a metric
	 * Auto create a new metric with a unique id if the send-in metric has no id
	 * Update metric if the send-in metric has id
	 * 
	 * @param entity metric
	 * @return newly saved/created metric
	 */
	@Override
	public Metric save(Metric entity) {
		if (entity.getId() == null) {
			entity.setId(currentIdx);
			currentIdx++;
		}
		data.put(entity.getId(), entity);
		return entity;
	}

	/**
	 * Find a metric
	 * 
	 * @param id id of the metric
	 * @return if found, return the metric
	 * if not found, return null
	 */
	@Override
	public Metric findOne(Integer id) {
		return data.get(id);
	}

}
