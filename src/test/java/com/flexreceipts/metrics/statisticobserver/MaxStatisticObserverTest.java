package com.flexreceipts.metrics.statisticobserver;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.MetricUnit;
import com.flexreceipts.metrics.model.Statistic;
import com.flexreceipts.metrics.model.StatisticUnit;
import com.flexreceipts.metrics.util.Constant;

/**
 * Test find max algorithm
 * 
 * @author Stefan
 *
 */
public class MaxStatisticObserverTest {
	
	private MaxStatisticObserver maxStatisticObserver;
	
	/**
	 * Setup
	 */
	@Before
	public void setup() {
		maxStatisticObserver = new MaxStatisticObserver();
	}
	
	/**
	 * Test algorithm with input: 1, 9, 2, 6, 9.9, 5
	 * 
	 */
	@Test
	public void testAlgorithm() {
		Statistic statistic = new Statistic();
		Metric m = new Metric();
		List<MetricUnit> metricUnits = new ArrayList<MetricUnit>();
		metricUnits.add(new MetricUnit(1f));
		metricUnits.add(new MetricUnit(9f));
		metricUnits.add(new MetricUnit(2f));
		metricUnits.add(new MetricUnit(6f));
		metricUnits.add(new MetricUnit(9.9f));
		metricUnits.add(new MetricUnit(5f));
		m.setMetricUnits(metricUnits);
		maxStatisticObserver.calculate(statistic, m);
		StatisticUnit statisticUnit = statistic.getStatisticUnits().get(Constant.MAX);
		assertEquals(9.9f, statisticUnit.getValue().floatValue(), 0);
	}
	
	/**
	 * Test algorithm with input: 1, 1
	 */
	@Test
	public void testAlgorithm1() {
		Statistic statistic = new Statistic();
		Metric m = new Metric();
		List<MetricUnit> metricUnits = new ArrayList<MetricUnit>();
		metricUnits.add(new MetricUnit(1f));
		metricUnits.add(new MetricUnit(1f));
		m.setMetricUnits(metricUnits);
		maxStatisticObserver.calculate(statistic, m);
		StatisticUnit statisticUnit = statistic.getStatisticUnits().get(Constant.MAX);
		assertEquals(1f, statisticUnit.getValue().floatValue(), 0);
	}
	
	/**
	 * Test algorithm with input: empty
	 */
	@Test
	public void testAlgorithmEmpty() {
		Statistic statistic = new Statistic();
		Metric m = new Metric();
		List<MetricUnit> metricUnits = new ArrayList<MetricUnit>();
		m.setMetricUnits(metricUnits);
		maxStatisticObserver.calculate(statistic, m);
		StatisticUnit statisticUnit = statistic.getStatisticUnits().get(Constant.MAX);
		assertEquals(null, statisticUnit);
	}
	
	/**
	 * Test algorithm with input: null
	 */
	@Test
	public void testAlgorithmNullInput() {
		Statistic statistic = new Statistic();
		maxStatisticObserver.calculate(statistic, null);
		StatisticUnit statisticUnit = statistic.getStatisticUnits().get(Constant.MAX);
		assertEquals(null, statisticUnit);
	}

}
