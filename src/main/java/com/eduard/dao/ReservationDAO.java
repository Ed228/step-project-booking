package com.eduard.dao;

import com.eduard.Reservation;

import java.util.List;

public interface ReservationDAO {
    void addReservation(Reservation reservation);
    boolean removeReservation(long id);
    List<Reservation> getReservationsByFirstAndLastName(String firstName, String lastName);
    List<Reservation> getAll();
    Reservation getReservationById(long id);
}
