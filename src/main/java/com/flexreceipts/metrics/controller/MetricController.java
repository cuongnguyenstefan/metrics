package com.flexreceipts.metrics.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.flexreceipts.metrics.controller.error.MetricBadArgumentExeption;
import com.flexreceipts.metrics.controller.error.MetricIdNotFoundException;
import com.flexreceipts.metrics.model.Metric;
import com.flexreceipts.metrics.model.MetricUnit;
import com.flexreceipts.metrics.model.Statistic;
import com.flexreceipts.metrics.service.FacadeMetricService;

/**
 * Main Controller where receive 4 API:
 *  - POST "/metrics": create a new metric
 *  - GET "/metrics/{id}": get metric at id {id}
 *  - POST "/metrics/{id}/units": add new values to list of units
 *  - GET "/metrics/{id}/statistics": get statistics of metric with id {id}
 * 
 * @author Stefan
 *
 */
@RestController
@RequestMapping("/metrics")
public class MetricController {

	/**
	 * General service for getting/saving data
	 * 
	 */
	@Autowired
	private FacadeMetricService facadeMetricService;
	
	/**
	 * Process request to create a new metric
	 * 
	 * @param metric receive metric object, name is required
	 * @return if successfully created, return a URI relate to new created resource
	 *  if false, return a bad argument exception - 400 HTTP code
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> createMetric(@RequestBody Metric metric) {
		Metric create = facadeMetricService.create(metric);
		if (create == null) {
			throw new MetricBadArgumentExeption();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(create.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
	/**
	 * Process request to get info of a specific id
	 * 
	 * @param id id of the metric
	 * @return if found, return the metric object
	 * if not found, return a id not found exception - 400 HTTP code
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Metric getMetric(@PathVariable Integer id) {
		Metric metric = facadeMetricService.getMetric(id);
		if (metric == null) {
			throw new MetricIdNotFoundException();
		}
		return metric;
	}
	
	/**
	 * Process request to add new values to a metric's unit list
	 * 
	 * @param update list of units
	 * @param id id of the adding metric
	 * @return ok if success
	 */
	@RequestMapping(value = "/{id}/units", method = RequestMethod.POST)
	public ResponseEntity<?> addMoreValues(@RequestBody List<MetricUnit> update, @PathVariable Integer id) {
		Metric addValues = facadeMetricService.addValues(id, update);
		if (addValues == null) {
			throw new MetricIdNotFoundException();
		}
		return ResponseEntity.ok().build();
	}

	/**
	 * Process request to return statistics of a metric
	 * 
	 * @param id id of the specific metric
	 * @return if found, return statistic of that metric
	 * if not found, return a id not found exception - 400 HTTP code 
	 */
	@RequestMapping(value = "/{id}/statistics", method = RequestMethod.GET)
	public Statistic getStatistic(@PathVariable Integer id) {
		Statistic statistic = facadeMetricService.getStatisticsFromMetricId(id);
		if (statistic == null) {
			throw new MetricIdNotFoundException();
		}
		return statistic;
	}

	/**
	 * When a request throws a MetricIdNotFoundException, the function will be called
	 * to return BAD_REQUEST error with message
	 * 
	 * @param response HttpServletResponse
	 * @throws IOException IOException
	 */
	@ExceptionHandler(MetricIdNotFoundException.class)
	void handleBadRequests(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid Metric id");
	}

	/**
	 * When a request throws a MetricBadArgumentExeption, the function will be called
	 * to return BAD_REQUEST error with message
	 * 
	 * @param response HttpServletResponse
	 * @throws IOException IOException
	 */
	@ExceptionHandler(MetricBadArgumentExeption.class)
	void handleBadArgument(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid Argument for Metric");
	}
}
