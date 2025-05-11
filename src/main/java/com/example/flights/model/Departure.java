// src/main/java/com/example/flights/model/Departure.java
package com.example.flights.model;

public class Departure {
    private String icao24;
    private String callsign;
    private String estDepartureAirport;
    private long firstSeen;
    private long lastSeen;

    public Departure() {
    }

    public Departure(String icao24, String callsign, String estDepartureAirport, long firstSeen, long lastSeen) {
        this.icao24 = icao24;
        this.callsign = callsign;
        this.estDepartureAirport = estDepartureAirport;
        this.firstSeen = firstSeen;
        this.lastSeen = lastSeen;
    }

    public String getIcao24() {
        return icao24;
    }

    public void setIcao24(String icao24) {
        this.icao24 = icao24;
    }

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public String getEstDepartureAirport() {
        return estDepartureAirport;
    }

    public void setEstDepartureAirport(String estDepartureAirport) {
        this.estDepartureAirport = estDepartureAirport;
    }

    public long getFirstSeen() {
        return firstSeen;
    }

    public void setFirstSeen(long firstSeen) {
        this.firstSeen = firstSeen;
    }

    public long getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(long lastSeen) {
        this.lastSeen = lastSeen;
    }




    /**
     * Přepíše výchozí toString() pro snadnější debug
     * a logování (vypíše všechna pole v jedné lince).
     */


    @Override
    public String toString() {
        return "Departure{" +
                "icao24='" + icao24 + '\'' +
                ", callsign='" + callsign + '\'' +
                ", estDepartureAirport='" + estDepartureAirport + '\'' +
                ", firstSeen=" + firstSeen +
                ", lastSeen=" + lastSeen +
                '}';
    }
}
