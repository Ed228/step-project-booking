package com.eduard.service;

import com.eduard.FlightException;
import com.eduard.Reservation;
import com.eduard.controller.FlightController;
import com.eduard.dao.ReservationDAO;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    private FlightController flightController;
    private ReservationDAO reservationDAO;

    public ReservationServiceImpl(FlightController flightController, ReservationDAO reservationDAO) {
        this.flightController = flightController;
        this.reservationDAO = reservationDAO;
    }

    @Override
    public void addReservation(Reservation reservation) throws FlightException {
        reservationDAO.addReservation(reservation);
        flightController.getById(reservation.getFlightId()).decrementFreeSeat(reservation.getCountOfSeats());
    }

    @Override
    public boolean removeReservation(long id) throws FlightException {
        Reservation reservationById = this.getReservationById(id);
        if(reservationById != null) {
            this.reservationDAO.removeReservation(id);
            this.flightController
                    .getById(reservationById.getFlightId())
                    .incrementFreeSeat((reservationById.getCountOfSeats()));
            return true;
        } else return false;
    }

    @Override
    public List<Reservation> getReservationsByFirstAndLastName(String firstName, String lastName) {
        return this.reservationDAO.getReservationsByFirstAndLastName(firstName, lastName);
    }

    @Override
    public List<Reservation> getAll() {
        return this.reservationDAO.getAll();
    }

    @Override
    public Reservation getReservationById(long id) {
        return this.reservationDAO.getReservationById(id);
    }
}
