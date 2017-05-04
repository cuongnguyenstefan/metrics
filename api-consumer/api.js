const Client = require('node-rest-client').Client;
const config = require('./config')

const client = new Client();

const host = config.host;
const port = config.port;

const linkcreator_base = function() {
	return "http://" + host + ":" + port + "/metrics";
};

const linkcreator_specific = function(id) {
	return "http://" + host + ":" + port + "/metrics/" + id;
};

const linkcreator_metricUnits = function(id) {
	return linkcreator_specific(id) + "/metricUnits";
};

const linkcreator_statistics = function(id) {
	return linkcreator_specific(id) + "/statistics";
};

const createMetric = function(data, callback) {
	
	const args = {
		data: data,
		headers: { "Content-Type": "application/json" }
	};
	
	const base = linkcreator_base();
	
	client.post(base, args, function (data, response) {
		// parsed response body as js object
		console.log(data);
		if (callback && typeof callback === "function") {
			callback(data);
		}
	});
	
	
};

const addValues = function(id, data, callback) {
	const args = {
		data: data,
		headers: { "Content-Type": "application/json" }
	};
	
	const base = linkcreator_metricUnits(id);
	
	client.post(base, args, function (data, response) {
		// parsed response body as js object
		console.log(data);
		
		if (callback && typeof callback === "function") {
			callback(data);
		}
		
	});
};

const getMetric = function(id, callback) {
	
	const base = linkcreator_specific(id);
	
	client.get(base, function (data) {
		console.log(data);
		if (callback && typeof callback === "function") {
			console.log("callback metric")
			callback(data);
		}
	});
}

const getStatistics = function (id, callback) {
	
	const base = linkcreator_statistics(id);
	
	client.get(base, function (data) {
		// parsed response body as js object
		console.log(data);
		
		if (callback && typeof callback === "function") {
			callback(data);
		}
	});
}

module.exports = {
	createMetric : createMetric,
	addValues : addValues,
	getMetric : getMetric, 
	getStatistics : getStatistics
};