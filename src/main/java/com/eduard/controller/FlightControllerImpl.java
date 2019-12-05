package com.eduard.controller;

import com.eduard.Cities;
import com.eduard.Flight;
import com.eduard.service.FlightsService;

import java.time.LocalDateTime;
import java.util.List;

public class FlightControllerImpl implements FlightController{

    private FlightsService flightsService;

    public FlightControllerImpl(FlightsService flightsService) {
        this.flightsService = flightsService;
    }

    @Override
    public List<Flight> getAll() {
        return flightsService.getAll();
    }

    @Override
    public Flight getById(int id) {
        return flightsService.getById(id);
    }

    @Override
    public List<Flight> searchByCityDateFreeSet(Cities city, String departureDateString, int freeSet) {
        return flightsService.searchByCityDateFreeSet(city, departureDateString, freeSet);
    }

    @Override
    public boolean updateSeatById(String id, String freeSeat) {
        return flightsService.updateSeatById(id, freeSeat);
    }
}
