package com.eduard.controller;

import com.eduard.FlightException;
import com.eduard.Reservation;

import java.util.List;

public interface ReservationController {
    void addReservation(Reservation reservation) throws FlightException;
    boolean removeReservation(long id) throws FlightException;
    List<Reservation> getReservationsByFirstAndLastName(String firstName, String lastName);
    List<Reservation> getAll();
    Reservation getReservationById(long id);
}
