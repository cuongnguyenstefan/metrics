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

import com.flexreceipts.metrics.model.Statistic;
import com.flexreceipts.metrics.repository.StatisticRepository;

/**
 * Test StatisticServiceImpl
 * 
 * @author Stefan
 *
 */
public class StatisticServiceImplTest {

	@Mock
	private StatisticRepository statisticRepository;

	@InjectMocks
	private StatisticServiceImpl statisticService;

	/**
	 * Setup the mock controller with the mock injection
	 */
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * Test create function
	 */
	@Test
	public void testCreate() {
		Statistic entity = new Statistic();
		when(statisticRepository.save(entity)).thenReturn(entity);
		statisticService.create();
		verify(statisticRepository, times(1)).save(any());
	}
	
	/**
	 * Test findOne when input normal
	 */
	@Test
	public void testFindOneWhenInputNormal() {
		Statistic value = new Statistic();
		when(statisticRepository.findOne(anyInt())).thenReturn(value );
		Statistic findOne = statisticService.findOne(1);
		assertEquals(value, findOne);
		verify(statisticRepository, times(1)).findOne(any());
	}
	
	/**
	 * Test save when input null id
	 */
	@Test
	public void testSaveWhenInputNullId() {
		Statistic value = new Statistic();
		Statistic findOne = statisticService.save(value);
		assertEquals(null, findOne);
		verify(statisticRepository, times(0)).save(any());
	}
	
	/**
	 * Test save when input null 
	 */
	@Test
	public void testSaveWhenInputNull() {
		Statistic findOne = statisticService.save(null);
		assertEquals(null, findOne);
		verify(statisticRepository, times(0)).save(any());
	}
	
	/**
	 * Test save when input id 0
	 */
	@Test
	public void testSaveWhenInputId0() {
		Statistic value = new Statistic();
		value.setId(0);
		Statistic findOne = statisticService.save(value);
		assertEquals(null, findOne);
		verify(statisticRepository, times(0)).save(any());
	}
	
	/**
	 * Test save when input normally
	 */
	@Test
	public void testSaveWhenInputNormal() {
		Statistic value = new Statistic();
		value.setId(1);
		when(statisticRepository.save(value)).thenReturn(value);
		Statistic findOne = statisticService.save(value);
		assertEquals(value, findOne);
		verify(statisticRepository, times(1)).save(any());
	}
	
}
