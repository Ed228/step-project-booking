package com.eduard.dao;

import com.eduard.FlightException;
import com.eduard.Reservation;
import com.eduard.controller.FlightController;

import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    private List<Reservation> reservations = new ArrayList<>();
    private FlightController flightController;

    public ReservationDAOImpl(FlightController flightController) {
        this.flightController = flightController;
    }

    @Override
    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
        try {
            flightController
                    .getById(reservation.getFlight().getId())
                    .decreaseFreeSeat(reservation.getCountOfSeats());
        } catch (FlightException e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeReservation(long id) {

    }

    @Override
    public List<Reservation> getReservationsByFirstAndLastName(String firstName, String lastName) {
        return null;
    }
}
