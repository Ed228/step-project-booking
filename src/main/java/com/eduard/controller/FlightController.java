package com.eduard.controller;

import com.eduard.Cities;
import com.eduard.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightController {

    List<Flight> getAll();
    Flight getById(int id);
    List<Flight> searchByCityDateFreeSet(Cities city, LocalDateTime departureDate, int freeSet);
    boolean updateSeatById(String id, String freeSeat);
}
