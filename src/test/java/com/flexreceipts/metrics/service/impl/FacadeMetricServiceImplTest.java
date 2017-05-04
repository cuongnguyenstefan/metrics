package com.flexreceipts.metrics.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.MetricUnit;
import com.flexreceipts.metrics.model.Statistic;
import com.flexreceipts.metrics.service.MetricService;
import com.flexreceipts.metrics.service.StatisticService;

/**
 * Test FacadeMetricService
 * 
 * @author Stefan
 *
 */
public class FacadeMetricServiceImplTest {

	@Mock
	private MetricService metricService;

	@Mock
	private StatisticService statisticService;

	@InjectMocks
	private FacadeMetricServiceImpl facadeMetricService;

	/**
	 * Setup the mock controller with the mock injection
	 */
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test create function when input Metric has no name
	 */
	@Test
	public void testCreateWithoutName() {
		Metric metric = new Metric();
		Metric create = facadeMetricService.create(metric);
		assertEquals(null, create);
		verify(metricService, times(0)).save(any());
		verify(statisticService, times(0)).save(any());
		verify(statisticService, times(0)).create();
	}

	/**
	 * Test create function when input Metric has empty name
	 */
	@Test
	public void testCreateWithEmptyName() {
		Metric metric = new Metric();
		metric.setName("");
		Metric create = facadeMetricService.create(metric);
		assertEquals(null, create);
		verify(metricService, times(0)).save(any());
		verify(statisticService, times(0)).save(any());
		verify(statisticService, times(0)).create();
	}

	/**
	 * Test create function when input Metric is normal
	 */
	@Test
	public void testCreateNormal() {
		Metric metric = new Metric();
		metric.setName("a");
		Statistic value = new Statistic();
		when(statisticService.create()).thenReturn(value);
		when(metricService.create(any())).thenReturn(metric);
		Metric create = facadeMetricService.create(metric);
		assertEquals("a", create.getName());
		verify(statisticService, times(1)).create();
	}

	/**
	 * Test getMetric when input an invalid value
	 */
	@Test
	public void testGetMetricWithInvalidInput() {
		when(metricService.findOne(anyInt())).thenReturn(null);
		Metric metric = facadeMetricService.getMetric(1);
		assertEquals(null, metric);
	}

	/**
	 * Test getMetric when input a normal value
	 */
	@Test
	public void testGetMetricWithValidInput() {
		Metric metric = new Metric();
		when(metricService.findOne(anyInt())).thenReturn(metric);
		Metric result = facadeMetricService.getMetric(1);
		assertEquals(metric, result);
	}

	/**
	 * Test addValues when input a null argument
	 */
	@Test
	public void testAddValuesWithNullInput() {
		Metric addValues = facadeMetricService.addValues(1, null);
		assertEquals(null, addValues);
	}

	/**
	 * Test addValues when input an empty array
	 */
	@Test
	public void testAddValuesWithEmptyArray() {
		List<MetricUnit> array = new ArrayList<MetricUnit>();
		Metric addValues = facadeMetricService.addValues(1, array);
		assertEquals(null, addValues);
	}

	/**
	 * Test addValues when input an Invalid ID
	 */
	@Test
	public void testAddValuesWithInvalidId() {
		List<MetricUnit> array = new ArrayList<MetricUnit>();
		array.add(new MetricUnit(1f));
		when(metricService.findOne(1)).thenReturn(null);
		Metric addValues = facadeMetricService.addValues(1, array);
		assertEquals(null, addValues);
	}

	/**
	 * Test addValues when input normally
	 */
	@Test
	public void testAddValuesNormally() {
		List<MetricUnit> array = new ArrayList<MetricUnit>();
		array.add(new MetricUnit(1f));
		Metric metric = new Metric();
		metric.setStatistics(1);
		metric.setId(1);
		Statistic value = new Statistic();
		when(statisticService.findOne(1)).thenReturn(value);
		when(metricService.findOne(1)).thenReturn(metric);
		when(metricService.save(any())).thenReturn(metric);
		Metric addValues = facadeMetricService.addValues(1, array);
		assertEquals(metric, addValues);
	}

	/**
	 * Test getStatistic when input invalid id
	 */
	@Test
	public void testGetStatisticsFromMetricIdWithInvalidID() {
		when(metricService.findOne(1)).thenReturn(null);
		Statistic statisticsFromMetricId = facadeMetricService.getStatisticsFromMetricId(1);
		assertEquals(null, statisticsFromMetricId);
	}

	/**
	 * Test getStatistic when input normally
	 */
	@Test
	public void testGetStatisticsFromMetricIdNormally() {
		Metric metric = new Metric();
		metric.setStatistics(1);
		Statistic value = new Statistic();
		when(metricService.findOne(1)).thenReturn(metric);
		when(statisticService.findOne(1)).thenReturn(value);
		Statistic statisticsFromMetricId = facadeMetricService.getStatisticsFromMetricId(1);
		assertEquals(value, statisticsFromMetricId);
	}
}

