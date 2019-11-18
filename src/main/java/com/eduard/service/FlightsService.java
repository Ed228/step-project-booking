package com.eduard.service;

import com.eduard.Cities;
import com.eduard.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightsService {
    List getAll();
    Flight getById(int id);
    Flight searchByCityDateFreeSet(Cities city, LocalDateTime departureDate, int freeSet);
    boolean updateSeatById(String id, String freeSeat);
}
