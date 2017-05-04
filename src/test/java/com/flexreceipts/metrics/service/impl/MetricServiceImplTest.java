package com.flexreceipts.metrics.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.repository.MetricRepository;

/**
 * Test MetricService
 * 
 * @author Stefan
 *
 */
public class MetricServiceImplTest {
	
	@Mock
	private MetricRepository metricRepository;

	@InjectMocks
	private MetricServiceImpl metricService;

	/**
	 * Setup the mock controller with the mock injection
	 */
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * Test create when input is null
	 */
	@Test
	public void testCreateWhenInputNull() {
		Metric create = metricService.create(null);
		assertEquals(null, create);
		verify(metricRepository, times(0)).save(any());
	}
	
	/**
	 * Test create when input not null id
	 */
	@Test
	public void testCreateWhenInputNotNullId() {
		Metric metric = new Metric();
		metric.setId(1);
		Metric create = metricService.create(metric);
		assertEquals(null, create);
		verify(metricRepository, times(0)).save(any());
	}
	
	/**
	 * Test create when input null name
	 */
	@Test
	public void testCreateWhenInputNotNullName() {
		Metric metric = new Metric();
		Metric create = metricService.create(metric);
		assertEquals(null, create);
		verify(metricRepository, times(0)).save(any());
	}
	
	/**
	 * Test create when input normal
	 */
	@Test
	public void testCreateWhenInputNormal() {
		Metric metric = new Metric();
		metric.setName("security");
		when(metricRepository.save(metric)).thenReturn(metric);
		Metric create = metricService.create(metric);
		assertEquals(metric, create);
		verify(metricRepository, times(1)).save(any());
	}
	
	/**
	 * Test findOne when input normal
	 */
	@Test
	public void testFindOneWhenInputNormal() {
		Metric value = new Metric();
		when(metricRepository.findOne(anyInt())).thenReturn(value);
		Metric findOne = metricService.findOne(1);
		assertEquals(value, findOne);
		verify(metricRepository, times(1)).findOne(any());
	}
	
	/**
	 * Test save when input null id
	 */
	@Test
	public void testSaveWhenInputNullId() {
		Metric value = new Metric();
		Metric findOne = metricService.save(value);
		assertEquals(null, findOne);
		verify(metricRepository, times(0)).save(any());
	}
	
	/**
	 * Test save when input null 
	 */
	@Test
	public void testSaveWhenInputNull() {
		Metric findOne = metricService.save(null);
		assertEquals(null, findOne);
		verify(metricRepository, times(0)).save(any());
	}
	
	/**
	 * Test save when input id 0
	 */
	@Test
	public void testSaveWhenInputId0() {
		Metric value = new Metric();
		value.setId(0);
		Metric findOne = metricService.save(value);
		assertEquals(null, findOne);
		verify(metricRepository, times(0)).save(any());
	}
	
	/**
	 * Test save when input normally
	 */
	@Test
	public void testSaveWhenInputNormal() {
		Metric value = new Metric();
		value.setId(1);
		when(metricRepository.save(value)).thenReturn(value);
		Metric findOne = metricService.save(value);
		assertEquals(value, findOne);
		verify(metricRepository, times(1)).save(any());
	}
	
}
