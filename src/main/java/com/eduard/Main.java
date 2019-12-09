package com.eduard;

import com.eduard.consoleReader.ConsoleReader;
import com.eduard.controller.FlightControllerImpl;
import com.eduard.controller.ReservationControllerImpl;
import com.eduard.dao.FlightDaoImpl;
import com.eduard.dao.ReservationDAOImpl;
import com.eduard.dataBase.DBofFile;
import com.eduard.service.FlightsServiceImpl;
import com.eduard.service.ReservationServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main1(String[] args) throws IOException, FlightException {
        DBofFile<Flight> dbFlights = new DBofFile<>("DataBaseRaces");
        DBofFile<Reservation> dbReservations = new DBofFile<>("DataBaseBooking");
        List<Flight> flights = dbFlights.getAll();
        ArrayList<Reservation> reservations = new ArrayList<>();
        /*------------------------------------------------------------------------*/
        FlightDaoImpl flightDao = new FlightDaoImpl(flights);
        FlightsServiceImpl flightsService = new FlightsServiceImpl(flightDao);
        FlightControllerImpl flightController = new FlightControllerImpl(flightsService);
        ReservationDAOImpl reservationDAO = new ReservationDAOImpl(reservations);
        ReservationServiceImpl reservationService = new ReservationServiceImpl(flightController, reservationDAO);
        ReservationControllerImpl reservationController = new ReservationControllerImpl(reservationService);
        /*------------------------------------------------------------------------*/
        ConsoleReader consoleReader = new ConsoleReader(flightController, reservationController, dbFlights, dbReservations);
        consoleReader.init();
        consoleReader.run();
    }

    public static void main(String[] args) {
        Reservation reservation = new Reservation(0, "valera", "zmishenko", 0,
                Arrays.asList(new Passenger("vasya", "pupkin"),
                        new Passenger("alex", "spak"))
        );
        System.out.println(reservation.getOtherPassengers());
        String ofDBSting = reservation.toDBSting();
        System.out.println(ofDBSting);
        Reservation reservationCopy = new Reservation().ofDBString(ofDBSting);
        System.out.println(reservationCopy.getOtherPassengers());

    }
}
