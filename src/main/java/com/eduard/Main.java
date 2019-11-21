package com.eduard;

import com.eduard.controller.FlightControllerImpl;
import com.eduard.dao.FlightDaoImpl;
import com.eduard.dao.ReservationDAOImpl;
import com.eduard.service.FlightsServiceImpl;

import java.io.*;
import java.time.*;
import java.util.*;
import java.util.function.Consumer;

public class Main {

    public static void main1(String[] args) throws IOException {
        List<Flight> flights = GeneratorFlightsFromCity.generate(Cities.KIEV, 15, LocalDateTime.now(ZoneId.of("GMT+02:00")));
        DBofFile<Flight> db = new DBofFile<>("DataBase");
        db.addMany(flights);

        FlightDaoImpl flightDao = new FlightDaoImpl(flights);
        FlightsServiceImpl flightsService = new FlightsServiceImpl(flightDao);
        FlightControllerImpl flightController = new FlightControllerImpl(flightsService);

        System.out.println(flightController.getById(4));
        System.out.println(flightController.searchByCityDateFreeSet(Cities.PARIS, LocalDateTime.parse("2019-11-19T03:03"), 4));
    }

    public static void main(String[] args) throws IOException {
        DBofFile<Flight> db = new DBofFile<>("DataBase");
        List<Flight> flights = db.getAll();
        FlightDaoImpl flightDao = new FlightDaoImpl(flights);
        FlightsServiceImpl flightsService = new FlightsServiceImpl(flightDao);
        FlightControllerImpl flightController = new FlightControllerImpl(flightsService);
        System.out.println(flightController.getById(0));
        ArrayList<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger("vitya", "shlyapik"));
        passengers.add(new Passenger("petya", "shlyapik"));
        Reservation reservation = new Reservation(0, "vasya", "pupkin", flightController.getAll().get(0), passengers);
        ReservationDAOImpl reservationDAO = new ReservationDAOImpl(flightController);
        reservationDAO.addReservation(reservation);
        System.out.println(flightController.getById(0));
    }

}
