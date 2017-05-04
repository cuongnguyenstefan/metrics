const api = require('./api');

const data_create_metric = {
								"name": "Securitys", 
								"description" : "desc" ,
								"metricUnits":
									[
										{"value":0.996},
										{"value":0.995}
									]
							};

const data_add_values = [
							{"value":0.996},
							{"value":0.995}
						];

						
// create metric's signature: createMetric(data, callback)
// default print data response
api.createMetric(data_create_metric);

// getMetric's signature: getMetric(id, callback)
// default print data response
api.getMetric(1);

// getStatistics's signature: getStatistics(id, callback)
// default print data response
api.getStatistics(1);

// addValues's signature: addValues(id, data, callback)
// default print data response
api.addValues(1, data_add_values);

