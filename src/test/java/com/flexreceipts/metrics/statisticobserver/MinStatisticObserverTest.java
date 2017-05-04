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
 * Test find min algorithm
 * 
 * @author Stefan
 *
 */
public class MinStatisticObserverTest {


	private MinStatisticObserver minStatisticObserver;
	
	/**
	 * Setup
	 */
	@Before
	public void setup() {
		minStatisticObserver = new MinStatisticObserver();
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
		metricUnits.add(new MetricUnit(0.1f));
		metricUnits.add(new MetricUnit(9f));
		metricUnits.add(new MetricUnit(2f));
		metricUnits.add(new MetricUnit(6f));
		metricUnits.add(new MetricUnit(9.9f));
		metricUnits.add(new MetricUnit(5f));
		m.setMetricUnits(metricUnits);
		minStatisticObserver.calculate(statistic, m);
		StatisticUnit statisticUnit = statistic.getStatisticUnits().get(Constant.MIN);
		assertEquals(0.1f, statisticUnit.getValue().floatValue(), 0);
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
		minStatisticObserver.calculate(statistic, m);
		StatisticUnit statisticUnit = statistic.getStatisticUnits().get(Constant.MIN);
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
		minStatisticObserver.calculate(statistic, m);
		StatisticUnit statisticUnit = statistic.getStatisticUnits().get(Constant.MIN);
		assertEquals(null, statisticUnit);
	}
	
	/**
	 * Test algorithm with input: null
	 */
	@Test
	public void testAlgorithmNullInput() {
		Statistic statistic = new Statistic();
		minStatisticObserver.calculate(statistic, null);
		StatisticUnit statisticUnit = statistic.getStatisticUnits().get(Constant.MIN);
		assertEquals(null, statisticUnit);
	}
	
}
