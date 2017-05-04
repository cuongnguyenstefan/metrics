package com.flexreceipts.metrics.repository.inmemory;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.flexreceipts.metrics.model.Metric;

/**
 * Test MetricInMemoryRepository
 * 
 * @author Stefan
 *
 */
public class MetricInMemoryRepositoryTest {
	
	/**
	 * Test MetricInMemoryRepository
	 */
	private MetricInMemoryRepository metricInMemoryRepository;
	
	/**
	 * Setup
	 */
	@Before
	public void setup() {
		metricInMemoryRepository = new MetricInMemoryRepository();
	}
	
	/**
	 * Test save when input has no id
	 */
	@Test
	public void testSaveWhenInputHasNoId() {
		Metric entity = new Metric();
		Metric save = metricInMemoryRepository.save(entity);
		assertEquals(1, save.getId().intValue());
	}


	/**
	 * Test save when input has id
	 */
	@Test
	public void testSaveWhenInputHasId() {
		Metric entity = new Metric();
		entity.setId(5);
		Metric save = metricInMemoryRepository.save(entity);
		assertEquals(5, save.getId().intValue());
	}
	
	/**
	 * Test save when input has id
	 */
	@Test
	public void testFindOneExistedObject() {
		Metric entity = new Metric();
		metricInMemoryRepository.save(entity);
		Metric save = metricInMemoryRepository.findOne(1);
		assertEquals(1, save.getId().intValue());
	}
	
	/**
	 * Test save when input has non-existed id
	 */
	@Test
	public void testFindOneNoneExistedObject() {
		Metric save = metricInMemoryRepository.findOne(4);
		assertEquals(null, save);
	}
	
}
