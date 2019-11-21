package com.eduard.controller;

import com.eduard.Reservation;

import java.util.List;

public interface ReservationController {
    void addReservation(Reservation reservation);
    void removeReservation(long id);
    List<Reservation> getReservationsByFirstAndLastName(String firstName, String lastName);
}
