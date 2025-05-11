package com.example.flights.service;

import com.example.flights.model.Departure;
import com.example.flights.provider.FlightsProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightsService {
    private final FlightsProvider provider;

    // injektujeme implementaci (OpenSkyFlightsProvider)
    public FlightsService(FlightsProvider provider) {
        this.provider = provider;
    }

    /**
     * Vrátí seznam odletů pro zadané letiště a časový interval.
     */
    public List<Departure> getDepartures(String airportIcao, long from, long until) {
        return provider.fetchDepartures(airportIcao, from, until);
    }
}
