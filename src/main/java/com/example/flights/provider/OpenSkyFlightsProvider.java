package com.example.flights.provider;

import com.example.flights.model.Departure;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.cache.annotation.Cacheable;

import java.util.Arrays;
import java.util.List;

@Component
public class OpenSkyFlightsProvider implements FlightsProvider {

    private final RestTemplate restTemplate = new RestTemplate();

    // základní URL z application.properties
    @Value("${opensky.api.url}")
    private String baseUrl;

    @Override
    @Cacheable("departures")
    public List<Departure> fetchDepartures(String airportIcao, long from, long until) {
        // Sestavíme URL: /api/flights/departure?airport=XXX&begin=YYYY&end=ZZZZ
        String url = String.format(
                "%s/flights/departure?airport=%s&begin=%d&end=%d",
                baseUrl, airportIcao, from, until);
        System.out.println("Calling OpenSky…"); // ověření v logu, zda funguje cache
        // (jestliže se v logu vypíše calling opensky víme, že app volá api
        //jestliže ne, ale výsledek se vypíše, tak víme, že cache funguje



        // Zavoláme API a dostaneme pole Departure
        Departure[] response = restTemplate.getForObject(url, Departure[].class);

        // Převedeme na List (kdyby bylo null, vrátíme prázdný seznam)
        return response != null
                ? Arrays.asList(response)
                : List.of();
    }
}
