package com.eduard.dao;

import com.eduard.Cities;
import com.eduard.Flight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class FlightDaoImpl implements FlightsDAO {
    private  List<Flight> flights = new ArrayList<>();

    public FlightDaoImpl(List<Flight> flights) {
        this.flights.addAll(flights);
    }

    @Override
    public List<Flight> getAll() {
        return this.flights;
    }

    @Override
    public Flight getById(int id) {
         return this.flights.stream().anyMatch(f -> f.getId() == id) ?
         this.flights.stream().filter(f -> f.getId() == id).collect(Collectors.toList()).get(0) :
         null;
    }

    @Override
    public Flight searchByCityDateFreeSet(Cities city, LocalDateTime departureDate, int freeSet) {
        return this.flights.stream()
                .anyMatch(f -> f.getDestination().name().equals(city.name()) && f.getDepartureDate().equals(departureDate) && f.getFreeSeat() == freeSet) ?
                this.flights.stream().filter(f -> f.getDestination().name().equals(city.name()) && f.getDepartureDate().equals(departureDate) && f.getFreeSeat() == freeSet)
                .collect(Collectors.toList()).get(0) : null;
    }

    @Override
    public boolean updateSeatById(String id, String freeSeat) {
        return false;
    }
}
