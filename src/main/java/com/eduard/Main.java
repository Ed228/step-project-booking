package com.eduard;

import com.eduard.dao.FlightDaoImpl;

import java.io.*;
import java.time.*;
import java.util.*;

public class Main {

    public static void main2(String[] args) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("GMT+02:00"));
        List<Flight> flights = GeneratorFlightsFromCity.generate(Cities.KIEV, 15, now);
        flights.forEach(flight -> System.out.println(flight.toDBSting()));
    }

    public static void main(String[] args) throws IOException {
        DBofFile<Flight> db = new DBofFile<>("DataBase");
        LocalDateTime now = LocalDateTime.now(ZoneId.of("GMT+02:00"));
        List<Flight> flights = GeneratorFlightsFromCity.generate(Cities.KIEV, 15, now);
        db.addMany(flights);
        FlightDaoImpl flightDao = new FlightDaoImpl(flights);
        System.out.println(flightDao.getById(20));
    }

}
