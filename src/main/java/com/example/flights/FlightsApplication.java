package com.example.flights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.cache.annotation.EnableCaching;

import com.example.flights.provider.FlightsProvider;
import com.example.flights.provider.OpenSkyFlightsProvider;
import com.example.flights.model.Departure;

import java.time.Instant;
import java.util.List;

@SpringBootApplication
@EnableCaching
public class FlightsApplication {
// …

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(FlightsApplication.class, args);
		FlightsProvider provider = ctx.getBean(OpenSkyFlightsProvider.class);
	}
}
