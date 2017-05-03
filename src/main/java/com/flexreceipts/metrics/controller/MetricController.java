package com.flexreceipts.metrics.controller;

import java.io.IOException;
import java.net.URI;

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
import com.flexreceipts.metrics.model.Statistic;
import com.flexreceipts.metrics.service.MetricService;

@RestController
@RequestMapping("/metrics")
public class MetricController {

	@Autowired
	private MetricService metricService;

	@RequestMapping(value = "/{id}/statistic", method = RequestMethod.GET)
	public Statistic getStatistic(@PathVariable Integer id) {
		Statistic statistic = metricService.getStatistics(id);
		if (statistic == null) {
			throw new MetricIdNotFoundException();
		}
		return statistic;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Metric getMetric(@PathVariable Integer id) {
		Metric metric = metricService.getMetric(id);
		if (metric == null) {
			throw new MetricIdNotFoundException();
		}
		return metric;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> createMetric(@RequestBody Metric metric) {
		Metric create = metricService.create(metric);
		if (create == null) {
			throw new MetricBadArgumentExeption();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(create.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<?> addMoreValues(@RequestBody Metric update, @PathVariable Integer id) {
		Metric addValues = metricService.addValues(id, update.getMetricUnits());
		if (addValues == null) {
			throw new MetricIdNotFoundException();
		}
		return ResponseEntity.ok().build();
	}

	@ExceptionHandler(MetricIdNotFoundException.class)
	void handleBadRequests(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid Metric id");
	}

	@ExceptionHandler(MetricBadArgumentExeption.class)
	void handleBadArgument(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid Argument for Metric");
	}
}
