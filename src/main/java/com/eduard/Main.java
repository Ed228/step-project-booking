package com.eduard;

import com.eduard.controller.FlightControllerImpl;
import com.eduard.dao.FlightDaoImpl;
import com.eduard.service.FlightsServiceImpl;

import java.io.*;
import java.time.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Flight> flights = GeneratorFlightsFromCity.generate(Cities.KIEV, 15, LocalDateTime.now(ZoneId.of("GMT+02:00")));
        DBofFile<Flight> db = new DBofFile<>("DataBase");
        db.addMany(flights);

        FlightDaoImpl flightDao = new FlightDaoImpl(flights);
        FlightsServiceImpl flightsService = new FlightsServiceImpl(flightDao);
        FlightControllerImpl flightController = new FlightControllerImpl(flightsService);

        System.out.println(flightController.getById(4));
        System.out.println(flightController.searchByCityDateFreeSet(Cities.PARIS, LocalDateTime.parse("2019-11-19T03:03"), 4));
    }

}
