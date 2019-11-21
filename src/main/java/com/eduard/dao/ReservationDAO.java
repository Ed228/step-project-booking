package com.eduard.dao;

import com.eduard.Reservation;

import java.util.List;

public interface ReservationDAO {
    void addReservation(Reservation reservation);
    void removeReservation(long id);
    List<Reservation> getReservationsByFirstAndLastName(String firstName, String lastName);
}
