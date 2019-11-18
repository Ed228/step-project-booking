package com.eduard.service;

import com.eduard.Cities;
import com.eduard.dao.FlightsDAO;
import com.eduard.Flight;

import java.time.LocalDateTime;
import java.util.List;

public class FlightsServiceImpl implements  FlightsService {

    private FlightsDAO flightsDAO;

    public FlightsServiceImpl(FlightsDAO flightsDAO) {
        this.flightsDAO = flightsDAO;
    }

    @Override
    public List<Flight> getAll() {
        return flightsDAO.getAll();
    }

    @Override
    public Flight getById(int id) {
        return flightsDAO.getById(id);
    }

    @Override
    public Flight searchByCityDateFreeSet(Cities city, LocalDateTime departureDate, int freeSet) {
        return flightsDAO.searchByCityDateFreeSet(city, departureDate, freeSet);
    }

    @Override
    public boolean updateSeatById(String id, String freeSeat) {
        return flightsDAO.updateSeatById(id, freeSeat);
    }
}
