// src/main/java/com/example/flights/provider/FlightsProvider.java
package com.example.flights.provider;

import com.example.flights.model.Departure;
import java.util.List;

/**
 * Abstrakce pro poskytovatele dat o odletech.
 */
public interface FlightsProvider {
    /**
     * Načte seznam odletů z daného letiště v rámci časového intervalu.
     *
     * @param airportIcao ICAO kód letiště
     * @param from        počátek intervalu (UNIX timestamp)
     * @param until       konec intervalu (UNIX timestamp)
     * @return seznam odletů
     */
    List<Departure> fetchDepartures(String airportIcao, long from, long until);
}
