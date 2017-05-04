package com.flexreceipts.metrics.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.MetricUnit;
import com.flexreceipts.metrics.model.Statistic;
import com.flexreceipts.metrics.service.FacadeMetricService;

/**
 * Metric Controller test
 * 
 * @author Stefan
 *
 */
public class MetricControllerTest {

	/**
	 * Mock MVC
	 */
	private MockMvc mockMvc;

	/**
	 * Mock FacadeMetricService
	 */
	@Mock
	private FacadeMetricService facadeMetricService;

	/**
	 * Mock Controller
	 */
	@InjectMocks
	private MetricController metricController;

	/**
	 * Setup the mock controller with the mock FacadeMetricService
	 */
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(metricController).build();
	}

	/**
	 * Test createMetric when input is wrong
	 * When input is wrong, the facade service is supposed to return null
	 * and return a BadRequest response
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateMetricWithWrongInput() throws Exception {
		when(facadeMetricService.create(any())).thenReturn(null);
		String content = "{}";
		this.mockMvc.perform(post("/metrics").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isBadRequest());
	}

	/**
	 * Test createMetric when input is normal
	 * Method should return an Created code
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateMetricWithNormalInput() throws Exception {
		Metric m = new Metric();
		m.setId(1);
		m.setName("ABC");
		when(facadeMetricService.create(any())).thenReturn(m);
		String content = "{\"name\" : \"security\"}";
		this.mockMvc.perform(post("/metrics").contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isCreated());
	}

	/**
	 * Test getMetric when id param is not existed in repository
	 * When the id is not existed in repository, facadeService is supposed
	 * to return null and request return is supposed to be BadRequest.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetMetricWithInvalidId() throws Exception {
		when(facadeMetricService.getMetric(1)).thenReturn(null);
		this.mockMvc.perform(get("/metrics/1")).andExpect(status().isBadRequest());
	}

	/**
	 * Test getMetric when input a valid id, it is supposed
	 * to return an OK reponse
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetMetricWithValidId() throws Exception {
		Metric metric = new Metric();
		metric.setId(1);
		when(facadeMetricService.getMetric(1)).thenReturn(metric);
		this.mockMvc.perform(get("/metrics/1")).andExpect(status().isOk());
	}

	/**
	 * Test addMoreValues function when input is wrong, the facadeService
	 * is supposed to return null, and the method is supposed tor eturn bad
	 * request response
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddMoreValuesWithWrongInput() throws Exception {
		List<MetricUnit> list = new ArrayList<MetricUnit>();
		when(facadeMetricService.addValues(1, list)).thenReturn(null);
		String content = "[]";
		this.mockMvc.perform(post("/metrics/1/metricUnits").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isBadRequest());
	}

	/**
	 * Test addMoreValues functions, when input is normal, the function
	 * is supposed to return an OK reponse
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testAddMoreValuesWithNormalInput() throws Exception {
		Metric m = new Metric();
		m.setId(1);
		when(facadeMetricService.addValues(anyInt(), anyList())).thenReturn(m);
		String content = "[{\"value\" : 15}]";
		this.mockMvc.perform(post("/metrics/1/metricUnits").contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isOk());
	}
	
	/**
	 * Test getStatistic when input an invalid id, the facadeService
	 * is supposed to return a null value and the functions is supposed
	 * to return a badrequest response
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetStatisticWithInvalidId() throws Exception {
		when(facadeMetricService.getStatisticsFromMetricId(1)).thenReturn(null);
		this.mockMvc.perform(get("/metrics/1/statistics")).andExpect(status().isBadRequest());
	}

	/**
	 * Test getStatistic when input is normal, the function is supposed
	 * to return OK response
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetStatisticWithValidId() throws Exception {
		Statistic statistic = new Statistic();
		when(facadeMetricService.getStatisticsFromMetricId(1)).thenReturn(statistic);
		this.mockMvc.perform(get("/metrics/1/statistics")).andExpect(status().isOk());
	}
}
