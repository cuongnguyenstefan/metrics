----------------
Metrics Web API
----------------

A web application which allows users to create multiple metrics and
post values to those metrics. Users can request a summary statistics
for those metrics.


----------------
Build & Run
----------------

Requirement:
	- Java 1.8
	- Maven 3+
	- Setup JAVA_HOME path
	- Setup M2_HOME and MAVEN_HOME path

	
Build and run:
	- Clone project from github
	- Change directory to project folder
	
	***Can run directly without build:
		- change directory to /packaged folder 
		- for Windows, "run.bat"
		- for Linux, "run.sh"

	***Build from project directory
		- On commandline: 
			mvn clean package
			cd target
			java -jar metrics-0.0.1-SNAPSHOT.jar
	
The application will request to use port 8080, please make
sure the port is not binded


----------------
API
----------------

By Default, the port in used is 8080
Example of an API: localhost:8080/metrics GET

1. Create a Metric:
	Request: 
		HTTP POST
		"/metrics"
		Body:
		{  
		   "name": "name",  //Required
		   "description": "description",  //Not required
		   "metricUnits":[   //Not required
			  {  
				 "value":1.0
			  },
			  {  
				 "value":1.0
			  }
		   ]
		}
	Response:
		HTTP 201 Created
		Location: http://localhost:8080/metrics/{id}
		
	
2. Get a Metric:
	Request: 
		HTTP GET
		"/metrics/{id}"
	Response:
	{  
	   "id":1,
	   "name": "name",
	   "description": "description",
	   "metricUnits":[  
		  {  
			 "value":1.0
		  },
		  {  
			 "value":1.0
		  }
	   ]
	}
	
	
3. Post Values to a Metric
	Request:
		HTTP POST
		"/metrics/{id}/metricUnits"
		Body:
		[  
		   {  
			  "value":1
		   },
		   {  
			  "value":1
		   },
		   ..
		]
	Response:
		HTTP 200 OK

		
4. Retrieve Statistics:
	Request:
		HTTP GET
		"/metrics/{id}/statistics"
	Response:
		{  
		   "id":1,
		   "statistics":[  
			  {  
				 "name":"Min Value",
				 "value":1.0
			  },
			  {  
				 "name":"Max Value",
				 "value":1.0
			  },
			  {  
				 "name":"Median",
				 "value":1.0
			  },
			  {  
				 "name":"Arithmetic Mean",
				 "value":1.0
			  }
		   ]
		}



----------------
Relating Infomation
----------------

Technologies used: 
	- Spring Boot
	- Mockito

Design pattern used:
	- MVC
	- Facade
	- Observer

Go to api-consumer/readme.md for client test functions

----------------
Author
----------------
Stefan Nguyen
