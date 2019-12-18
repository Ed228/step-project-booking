package com.eduard.controller;

import com.eduard.model.FlightException;
import com.eduard.model.Reservation;

import java.util.List;

public interface ReservationController {
    void addReservation(Reservation reservation) throws FlightException;
    boolean removeReservation(long id) throws FlightException;
    List<Reservation> getReservationsByFirstAndLastName(String firstName, String lastName);
    List<Reservation> getAll();
    Reservation getReservationById(long id);
}
