package com.eduard;

import com.eduard.controller.FlightControllerImpl;
import com.eduard.controller.ReservationControllerImpl;
import com.eduard.dao.FlightDaoImpl;
import com.eduard.dao.ReservationDAOImpl;
import com.eduard.service.FlightsServiceImpl;
import com.eduard.service.ReservationServiceImpl;

import java.io.*;
import java.lang.reflect.Array;
import java.time.*;
import java.util.*;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) throws IOException, FlightException {
        DBofFile<Flight> db = new DBofFile<>("DataBase");
        List<Flight> flights = db.getAll();
        /*------------------------------------------------------------------------*/
        FlightDaoImpl flightDao = new FlightDaoImpl(flights);
        FlightsServiceImpl flightsService = new FlightsServiceImpl(flightDao);
        FlightControllerImpl flightController = new FlightControllerImpl(flightsService);
        /*------------------------------------------------------------------------*/
        ReservationDAOImpl reservationDAO = new ReservationDAOImpl();
        ReservationServiceImpl reservationService = new ReservationServiceImpl(flightController, reservationDAO);
        ReservationControllerImpl reservationController = new ReservationControllerImpl(reservationService);
        System.out.println("/*------------------------------------------------------------------------*/");
        System.out.println(flightController.getById(4));
        reservationController.addReservation(new Reservation(0, "alex", "shlyapik",4));
        System.out.println(flightController.getById(4));
        System.out.println(reservationController.getAll());
        System.out.println("/*------------------------------------------------------------------------*/");
        System.out.println(flightController.getById(5));
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger("vasya", "pupkin"));
        passengers.add(new Passenger("gosha", "kucenko"));
        passengers.add(new Passenger("valera", "zmishenko"));
        reservationController.addReservation(new Reservation(1, "anton", "pitkin", 5, passengers));
        System.out.println(flightController.getById(5));
        System.out.println(reservationController.getAll());
        System.out.println("/*------------------------------------------------------------------------*/");
        System.out.println(reservationController.getReservationsByFirstAndLastName("gosha", "kucenko"));
        System.out.println("/*------------------------------------------------------------------------*/");
        System.out.println(reservationController.getReservationsByFirstAndLastName("goga", "lil"));
        System.out.println("/*------------------------------------------------------------------------*/");
        System.out.println(reservationController.getAll());
        System.out.println(reservationController.removeReservation(0));
        System.out.println(reservationController.getAll());
        System.out.println(flightController.getById(4));
        System.out.println("/*------------------------------------------------------------------------*/");
        System.out.println(reservationController.getReservationById(1));
    }

}
