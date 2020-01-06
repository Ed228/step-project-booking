package com.eduard;

import com.eduard.consoleReader.ConsoleReader;
import com.eduard.controller.FlightControllerImpl;
import com.eduard.controller.ReservationControllerImpl;
import com.eduard.dao.FlightDaoImpl;
import com.eduard.dao.ReservationDAOImpl;
import com.eduard.dataBase.DBofFile;
import com.eduard.model.Flight;
import com.eduard.model.FlightException;
import com.eduard.model.Reservation;
import com.eduard.service.FlightsServiceImpl;
import com.eduard.service.ReservationServiceImpl;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, FlightException {
        DBofFile<Flight> dbFlights = new DBofFile<>("DataBaseRaces");
        DBofFile<Reservation> dbReservations = new DBofFile<>("DataBaseBooking");
        List<Flight> flights = dbFlights.getAllFlights();
        List<Reservation> reservations = dbReservations.getAllReservations();
        /*------------------------------------------------------------------------*/
        FlightDaoImpl flightDao = new FlightDaoImpl(flights);
        FlightsServiceImpl flightsService = new FlightsServiceImpl(flightDao);
        FlightControllerImpl flightController = new FlightControllerImpl(flightsService);
        ReservationDAOImpl reservationDAO = new ReservationDAOImpl(reservations);
        ReservationServiceImpl reservationService = new ReservationServiceImpl(flightsService, reservationDAO);
        ReservationControllerImpl reservationController = new ReservationControllerImpl(reservationService);
        /*------------------------------------------------------------------------*/
        ConsoleReader consoleReader = new ConsoleReader(flightController, reservationController, dbFlights, dbReservations);
        consoleReader.init();
        consoleReader.run();
    }
}
