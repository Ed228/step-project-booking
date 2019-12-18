package com.eduard.service;

import com.eduard.model.Cities;
import com.eduard.model.Flight;
import com.eduard.dao.FlightsDAO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FlightsServiceImpl implements  FlightsService {

    private FlightsDAO flightsDAO;

    public FlightsServiceImpl(FlightsDAO flightsDAO) {
        this.flightsDAO = flightsDAO;
    }

    @Override
    public List<Flight> getAll() {
        return filterFlightsPer24H(flightsDAO.getAll());
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

    private List<Flight> filterFlightsPer24H(List<Flight> flightList){
        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime nowDateTimePer24Hours = LocalDateTime.of(
                nowDateTime.getYear(), nowDateTime.getMonth(), nowDateTime.getDayOfMonth() + 1, nowDateTime.getHour(), nowDateTime.getMinute(), nowDateTime.getSecond(), nowDateTime.getNano()
        );
        return flightList.stream().filter(f -> {
            LocalDateTime dd = f.getDepartureDate();
            return (dd.getYear() >= nowDateTime.getYear()) && (dd.getYear() <= nowDateTimePer24Hours.getYear()) &&
                    (dd.getMonth().getValue() >= nowDateTime.getMonth().getValue() && (dd.getMonth().getValue() <= nowDateTimePer24Hours.getMonth().getValue()) &&
                            (dd.getDayOfMonth() >= nowDateTime.getDayOfMonth()) && (dd.getDayOfMonth() <= nowDateTimePer24Hours.getDayOfMonth()) &&
                            ((dd.getDayOfMonth() == nowDateTime.getDayOfMonth() && dd.getHour() >= nowDateTime.getHour()) ||
                                    (dd.getDayOfMonth() == nowDateTimePer24Hours.getDayOfMonth() && dd.getHour() <= nowDateTimePer24Hours.getHour()))
                    );
        }).collect(Collectors.toList());
    }
}
