package com.eduard.service;

import com.eduard.Cities;
import com.eduard.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightsService {
    List<Flight> getAll();
    Flight getById(int id);
    List<Flight> searchByCityDateFreeSet(Cities city, String departureDateString, int freeSet);
    boolean updateSeatById(String id, String freeSeat);
}
