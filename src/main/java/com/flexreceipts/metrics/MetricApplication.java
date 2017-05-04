package com.flexreceipts.metrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Application booter
 * 
 * @author Stefan
 *
 */
@SpringBootApplication
public class MetricApplication {
	
	/**
	 * Main function
	 * 
	 * @param args list of args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MetricApplication.class, args);
	}
	
}
