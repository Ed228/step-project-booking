package com.eduard.service;

import com.eduard.model.FlightException;
import com.eduard.model.Reservation;
import com.eduard.controller.FlightController;
import com.eduard.dao.ReservationDAO;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    private FlightsService flightService;
    private ReservationDAO reservationDAO;

    public ReservationServiceImpl(FlightsService flightsService, ReservationDAO reservationDAO) {
        this.flightService = flightsService;
        this.reservationDAO = reservationDAO;
    }

    @Override
    public void addReservation(Reservation reservation) throws FlightException {
        reservationDAO.addReservation(reservation);
        flightService.getById(reservation.getFlightId()).decrementFreeSeat(reservation.getCountOfSeats());
    }

    @Override
    public boolean removeReservation(long id) throws FlightException {
        Reservation reservationById = this.getReservationById(id);
        if(reservationById != null) {
            this.reservationDAO.removeReservation(id);
            this.flightService
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
