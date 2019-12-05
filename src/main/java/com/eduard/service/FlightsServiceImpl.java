package com.eduard.service;

import com.eduard.Cities;
import com.eduard.Flight;
import com.eduard.dao.FlightsDAO;
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
    public List<Flight> searchByCityDateFreeSet(Cities city, String departureDateString, int freeSet) {
        return flightsDAO.searchByCityDateFreeSet(city, departureDateString, freeSet);
    }

    @Override
    public boolean updateSeatById(String id, String freeSeat) {
        return flightsDAO.updateSeatById(id, freeSeat);
    }
}
