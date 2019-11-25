package com.eduard.service;

import com.eduard.FlightException;
import com.eduard.Reservation;

import java.util.List;

public interface ReservationService {
    void addReservation(Reservation reservation) throws FlightException;
    boolean removeReservation(long id) throws FlightException;
    List<Reservation> getReservationsByFirstAndLastName(String firstName, String lastName);
    List<Reservation> getAll();
    Reservation getReservationById(long id);
}
