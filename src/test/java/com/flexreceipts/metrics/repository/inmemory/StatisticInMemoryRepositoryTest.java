package com.flexreceipts.metrics.repository.inmemory;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.flexreceipts.metrics.model.Statistic;

/**
 * Test StatisticInMemoryRepository
 * 
 * @author Stefan
 *
 */
public class StatisticInMemoryRepositoryTest {

	/**
	 * Test MetricInMemoryRepository
	 */
	private StatisticInMemoryRepository statisticInMemoryRepository;
	
	/**
	 * Setup
	 */
	@Before
	public void setup() {
		statisticInMemoryRepository = new StatisticInMemoryRepository();
	}
	
	/**
	 * Test save when input has no id
	 */
	@Test
	public void testSaveWhenInputHasNoId() {
		Statistic entity = new Statistic();
		Statistic save = statisticInMemoryRepository.save(entity);
		assertEquals(1, save.getId().intValue());
	}


	/**
	 * Test save when input has id
	 */
	@Test
	public void testSaveWhenInputHasId() {
		Statistic entity = new Statistic();
		entity.setId(5);
		Statistic save = statisticInMemoryRepository.save(entity);
		assertEquals(5, save.getId().intValue());
	}
	
	/**
	 * Test save when input has id
	 */
	@Test
	public void testFindOneExistedObject() {
		Statistic entity = new Statistic();
		statisticInMemoryRepository.save(entity);
		Statistic save = statisticInMemoryRepository.findOne(1);
		assertEquals(1, save.getId().intValue());
	}
	
	/**
	 * Test save when input has non-existed id
	 */
	@Test
	public void testFindOneNoneExistedObject() {
		Statistic save = statisticInMemoryRepository.findOne(4);
		assertEquals(null, save);
	}

	
}
