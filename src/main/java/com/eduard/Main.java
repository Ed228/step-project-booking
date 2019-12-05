package com.eduard;

import com.eduard.consoleReader.ConsoleReader;
import com.eduard.controller.FlightControllerImpl;
import com.eduard.controller.ReservationControllerImpl;
import com.eduard.dao.FlightDaoImpl;
import com.eduard.dao.ReservationDAOImpl;
import com.eduard.service.FlightsServiceImpl;
import com.eduard.service.ReservationServiceImpl;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, FlightException {
        DBofFile<Flight> db = new DBofFile<>("DataBase");
        List<Flight> flights = db.getAll();
        /*------------------------------------------------------------------------*/
        FlightDaoImpl flightDao = new FlightDaoImpl(flights);
        FlightsServiceImpl flightsService = new FlightsServiceImpl(flightDao);
        FlightControllerImpl flightController = new FlightControllerImpl(flightsService);
        ReservationDAOImpl reservationDAO = new ReservationDAOImpl();
        ReservationServiceImpl reservationService = new ReservationServiceImpl(flightController, reservationDAO);
        ReservationControllerImpl reservationController = new ReservationControllerImpl(reservationService);
        /*------------------------------------------------------------------------*/
        ConsoleReader consoleReader = new ConsoleReader(flightController, reservationController);
        consoleReader.init();
        consoleReader.run();
    }
}
