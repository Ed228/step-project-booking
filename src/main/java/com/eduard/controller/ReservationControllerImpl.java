package com.eduard.controller;

import com.eduard.FlightException;
import com.eduard.Reservation;
import com.eduard.service.ReservationService;

import java.util.List;

public class ReservationControllerImpl implements ReservationController {

    private ReservationService reservationService;

    public ReservationControllerImpl(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public void addReservation(Reservation reservation) throws FlightException {
        this.reservationService.addReservation(reservation);
    }

    @Override
    public boolean removeReservation(long id) throws FlightException {
        return this.reservationService.removeReservation(id);
    }

    @Override
    public List<Reservation> getReservationsByFirstAndLastName(String firstName, String lastName) {
        return this.reservationService.getReservationsByFirstAndLastName(firstName, lastName);
    }

    @Override
    public List<Reservation> getAll() {
        return this.reservationService.getAll();
    }

    @Override
    public Reservation getReservationById(long id) {
        return this.reservationService.getReservationById(id);
    }
}
