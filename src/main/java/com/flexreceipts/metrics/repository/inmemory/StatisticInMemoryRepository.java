package com.flexreceipts.metrics.repository.inmemory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flexreceipts.metrics.model.Statistic;
import com.flexreceipts.metrics.repository.StatisticRepository;

/**
 * Repository of Statistics, which is implementing an in-memory solution
 * 
 * @author Stefan
 *
 */
@Repository
public class StatisticInMemoryRepository implements StatisticRepository {

	/**
	 * Statistics, store by map with id as a key
	 * Giving save and find an O(1) runtime
	 * 
	 */
	private Map<Integer, Statistic> data = new HashMap<Integer, Statistic>();

	/**
	 * ID of the next statistic
	 */
	private int currentIdx = 1;

	/**
	 * Save/create a statistic
	 * Auto create a new statistic with a unique id if the send-in statistic has no id
	 * Update statistic if the send-in statistic has id
	 * 
	 * @param entity statistic
	 * @return newly saved/created statistic
	 */
	@Override
	public Statistic save(Statistic entity) {
		if (entity.getId() == null) {
			entity.setId(currentIdx);
			currentIdx++;
		}
		data.put(entity.getId(), entity);
		return entity;
	}

	/**
	 * Find a statistic
	 * 
	 * @param id id of the statistic
	 * @return if found, return the statistic
	 * if not found, return null
	 */
	@Override
	public Statistic findOne(Integer id) {
		return data.get(id);
	}

}
