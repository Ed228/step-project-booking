package com.eduard.dao;

import com.eduard.model.Cities;
import com.eduard.model.Flight;

import java.util.List;


public interface FlightsDAO {
     List<Flight> getAll();
     Flight getById(int id);
     List<Flight> searchByCityDateFreeSet(Cities city, String departureDateString, int freeSet);
     boolean updateSeatById(String id, String freeSeat);
}
